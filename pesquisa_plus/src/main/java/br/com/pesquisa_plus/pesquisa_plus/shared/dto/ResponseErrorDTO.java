package br.com.pesquisa_plus.pesquisa_plus.shared.dto;

public record ResponseErrorDTO(int statusCode, String type, String timestamp, String message) {
} 
