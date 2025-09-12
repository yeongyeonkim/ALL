terraform {
  required_version = ">= 1.0.0"
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = ">= 4.0"
    }
  }
}

provider "aws" {
  region = var.aws_region
}

module "eks" {
  source             = "./modules/eks"
  name               = var.eks_name
  principal_arn      = var.eks_principal_arn
  kubernetes_version = var.kubernetes_version
  subnet_ids         = var.subnet_ids
  vpc_id             = var.vpc_id
}

module "iam" {
  source = "./modules/iam"
  
}
