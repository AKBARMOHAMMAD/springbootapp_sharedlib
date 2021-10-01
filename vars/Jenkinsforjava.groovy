def call(String repoUrl){
    pipeline{
        agent any
        tools{
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
    }
    stages{
        stage('Tools Initialzation'){
            steps{
                sh 'mvn --version'
                sh 'java -version'
            }
        }
        stage('checkout code'){
            steps{
                git branch: 'master',
                url : "${repoUrl}"
            }
        }
        stage('cleaning workspace'){
            steps{
                sh 'mvn clean'
            }
        }
        stage('Running test case'){
            steps{
                sh 'mvn test'
            }
        }
        stage('packaging application'){
            steps{
                sh 'mvn package -DskipTests'
                sh 'mv target/*.war target/my.war'
            }
        }
       // stage('deploy'){
       //     steps{
                
         //   }
       // }
    }
}
}
