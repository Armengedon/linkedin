package com.ub.service;

@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}