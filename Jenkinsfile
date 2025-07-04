@Library('jenkins-shared-lib-v2') _

def emailMessageSuccess = """================================ 
Test automation SUCCESS execution 
================================ 

# Report link: https://s3.amazonaws.com/dev.airmiles.ca/travel2.0-miles/${env.BUILD_NUMBER}/index.html 

# Jenkins execution link: https://jenkinstein.loyalty.com/job/travel2-finance-test-automation/${BUILD_NUMBER}/ 

# Jenkins build number execution: ${BUILD_NUMBER} 

# Status: SUCCESS"""

def emailMessageFailure = """================================ 
Test automation FAILURE execution 
================================ 

# Report link: https://s3.amazonaws.com/dev.airmiles.ca/travel2.0-miles/${env.BUILD_NUMBER}/index.html 

# Jenkins execution link: https://jenkinstein.loyalty.com/job/travel2-finance-test-automation/${BUILD_NUMBER}/ 

# Jenkins build number execution: ${BUILD_NUMBER} 

# Status: FAILURE"""

def emailMessageAborted = """================================ 
Test automation ABORTED execution 
================================ 

# Jenkins execution link: https://jenkinstein.loyalty.com/job/travel2-finance-test-automation/${BUILD_NUMBER}/ 

# Jenkins build number execution: ${BUILD_NUMBER} 

# Status: ABORTED"""

def mailTo = "cdiazpinzon@loyalty.com"

pipeline {
    agent {
        dockerfile {                   
            label 'aws-ec2'
            args '-u root:root'
        }
    }

    options {
        skipDefaultCheckout(true) // evitamos que el SCM corra automáticamente para hacer limpieza primero
    }

    parameters {
        choice(name: "ENVIRONMENTS", choices: ["uat", "load", "dev", "int"], description: "Select environment")
        string(name: "TAGS", defaultValue: "@enrollment", description: "Enter tags for Cucumber tests")
        booleanParam(name: 'DEFAULT', defaultValue: true, description: 'Use UAT and Regression tag if true')
    }

    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Run Tests') {
            steps {
                script {
                    sh """
                        echo ":::::..... The test cases are running with configuration: .....:::::"
                        echo Environment: ${params.ENVIRONMENTS}
                        chmod +x gradlew
                        ./gradlew clean test -Dspring.profiles.active=${params.ENVIRONMENTS} -Dcucumber.filter.tags="${params.TAGS}"
                    """
                }
            }
        }
    }

    post {
        always {
            script {
                echo "Cleaning up or post-processing"
            }
        }

        success {
            script {
                mail(
                    subject: "Test automation SUCCESS execution ${env.BUILD_NUMBER}",
                    body: emailMessageSuccess,
                    to: mailTo
                )
            }
        }

        failure {
            script {
                mail(
                    subject: "Test automation FAILURE execution ${env.BUILD_NUMBER} :(",
                    body: emailMessageFailure,
                    to: mailTo
                )
            }
        }

        aborted {
            script {
                mail(
                    subject: "Test automation ABORTED execution ${env.BUILD_NUMBER} :(",
                    body: emailMessageAborted,
                    to: mailTo
                )
            }
        }
    }
}
