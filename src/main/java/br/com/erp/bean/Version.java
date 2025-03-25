package br.com.erp.bean;

import lombok.Builder;

@Builder(toBuilder = true)
public record Version(String gitVersion, String dateTime) {

}