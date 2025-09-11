#### AWS ####

variable "aws_region" {
  default = "ap-northeast-2"
}

variable "vpc_id" {
  
}

variable "subnet_ids" {
    type = list(string) 
}

#### EKS ####

variable "eks_name" {
  
}

variable "kubernetes_version" {
  
}

variable "eks_principal_arn" {
  
}


#### IAM ####