package com.jk.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cloud-provider")
public interface UserService extends UserServer {

}
