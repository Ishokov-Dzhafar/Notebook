#!groovy
pipeline {
    agent {
        docker {
            image 'jangrewe/gitlab-ci-android'
        }
    }
    stages {
        stage("init") {
            steps{
                checkout([
                $class: 'GitSCM',
                branches: [[name: '*/develop']],
                doGenerateSubmoduleConfigurations: false,
                extensions: [[$class: 'CleanCheckout']],
                submoduleCfg: [],
                userRemoteConfigs: [[url: 'https://github.com/Ishokov-Dzhafar/Notebook']]
            ])
            }
        }
        stage("detekt") {
            steps{
                sh './gradlew detekt'
            }
        }
        stage("test") {
            steps{
                sh './gradlew testDebugUnitTest'
            }
        }
         stage("build") {
            steps{
                sh './gradlew clean assembleDebug'
            }
        }
        stage("lscpu") {
            steps{
                sh 'lscpu && free -m'
            }
        }
    }
}
