variable "vpc_id" {
  
}

variable "subnet_ids" {
    type = list(string)
  
}

variable "name" {
  
}

variable "kubernetes_version" {
  
}


variable "principal_arn" {
    type = "string"
}