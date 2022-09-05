package com.tcc.api.resources.swagger.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PagedModel<T> {

    private List<T> content;
    @ApiModelProperty(example = "10", value = "Quantidade de registros por página")
    private int size;
    @ApiModelProperty(example = "50", value = "Total de registros")
    private Long totalElements;
    @ApiModelProperty(example = "5", value = "Total de páginas")
    private Long totalPages;
    @ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
    private int number;

    public PagedModel() {
    }

    public PagedModel(Page<?> page, List<T> list) {
        this.number = page.getNumber();
        this.size = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = (long) page.getTotalPages();
        this.content = list;
    }


    public PagedModel<T> toPageModel(Page<?> page) {
        var pagedModel = new PagedModel<T>();

        pagedModel.setNumber(page.getNumber());
        pagedModel.setSize(page.getSize());
        pagedModel.setTotalElements(page.getTotalElements());
        pagedModel.setTotalPages(page.getTotalElements());

        return pagedModel;
    }


}
