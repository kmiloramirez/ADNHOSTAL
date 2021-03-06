@Library('ceiba-jenkins-library') _
pipeline{
	// any -> tomaria slave 5 u 8
	// Para mobile se debe especificar el slave -> {label 'Slave_Mac'}
	// Para proyectos de arus se debe tomar el slave 6 o 7 -> {label 'Slave6'} o {label 'Slave7'}
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        disableConcurrentBuilds()
    }

    environment {
        PROJECT_PATH_BACK = 'microservicio'
    }

    triggers {
        // @yearly, @annually, @monthly, @weekly, @daily, @midnight, and @hourly o definir un intervalo. Ej: H */4 * * 1-5
        pollSCM('@hourly') //define un intervalo regular en el que Jenkins debería verificar los cambios de fuente nuevos
    }

    tools {
        jdk 'JDK11_Centos'
        gradle 'Gradle5.6_Centos'
    }

    // Parametros disponibles en jenkins
     /*parameters{
            string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
            text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')
            booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')
            choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')
            password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a passwor')
     }*/

    stages{
        stage('Checkout') {
            steps {
                echo '------------>Checkout<------------'
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        gitTool: 'Default',
                        submoduleCfg: [],
                        userRemoteConfigs: [[
                            credentialsId: 'GitHub_kmiloramirez',
                            url:'https://github.com/kmiloramirez/ADNHOSTAL'
                        ]]
                    ])
            }
        }
        stage('Compilacion y Test Unitarios'){
            steps {
                echo '------------>Test Backend<------------'
                dir("${PROJECT_PATH_BACK}"){
                    sh 'chmod +x gradlew'
                    sh './gradlew clean'
                    sh './gradlew --stacktrace test'
                }
            }
            post{
                always {
                    junit '**/build/test-results/test/*.xml' //Configuración de los reportes de JUnit
                }
            }
        }
		
		stage('Static Code Analysis') {
			steps{
				sonarqubeMasQualityGatesP(sonarKey:'co.com.ceiba.adn:hostal-juan.ramirez',
				sonarName:'CeibaADN-Hostaljuan.ramirez',
				sonarPathProperties:'./sonar-project.properties')
			}
		}

        stage('Build'){
            steps{
                echo "------------>Compilación backend<------------"
                    dir("${PROJECT_PATH_BACK}"){
                        sh './gradlew build -x test'
                    }
                }
            }
        }

    post {
        failure {
            echo 'This will run only if failed'
            mail(
                to: 'juan.ramirez@ceiba.com.co',
                body:"Something is wrong with ${env.BUILD_URL}",
                subject: "Failed Pipeline:${currentBuild.fullDisplayName}"
            )
        }
        success {
            echo 'This will run only if successful'
        }
    }
}
