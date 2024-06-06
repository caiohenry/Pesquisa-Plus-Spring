package br.com.pesquisa_plus.pesquisa_plus.shared.dto;

import java.util.List;

public class PageableDTO<T> {
    private List<T> data;
    private long totalElements;

    public PageableDTO(List<T> data, long totalElements) {
        this.data = data;
        this.totalElements = totalElements;
    }

    // Getters e setters
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
