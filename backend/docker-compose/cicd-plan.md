# CI
# CD
# Plan
There are 
- backend modules, 
- external modules, 
- Kafka infrastructure 
- database and 
- UI
- (Metrics monitoring and performance testing) later

Honestly no clue how to do this? I think CI is easy with Docker and Jenkins.
Alternative is to go for Github actions + Github registry + Kubernetes cluster + Argo cd + Manifest/Helm + kubernetes

In any case need to Dockerize this.

I want to deploy and make it accessible over AWS. That will be a dream.

Need a sister project to 
- Kubernetes cluster in AWS (We could try EKSCTL)