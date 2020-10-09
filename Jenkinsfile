#!groovy
pipeline {
    agent {
        docker {
            image 'jangrewe/gitlab-ci-android'
        }
    }
    stages {
        stage("init") {
            steps {
                checkout([
                $class: 'GitSCM',
                branches: [[name: '*/jenkins']],
                userRemoteConfigs: [[url: 'https://github.com/Ishokov-Dzhafar/Notebook']]
            ])
            }
        }
        stage("clean project") {
            steps {
                sh './gradlew clean'
            }
        }
        stage("detekt") {
            steps {
                sh './gradlew detekt'
            }
        }
        stage("test") {
            steps {
                sh './gradlew testDebugUnitTest'
            }
        }
        stage("build") {
            steps {
                sh './gradlew assembleDebug'
            }
        }
    }
}
