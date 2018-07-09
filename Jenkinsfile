pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew clean'
                sh './gradlew compileJava'
            }
        }
        stage('Checkstyle') {
            steps {
                sh './gradlew checkstyleMain'
            }
        }
        stage('Test') {
            steps {
                timeout(20) {
                  sh './gradlew  test'
                }
            }
        }
    }
}
