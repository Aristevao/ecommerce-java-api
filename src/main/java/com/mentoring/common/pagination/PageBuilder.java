package com.mentoring.common.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PageBuilder {

    public static final int DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_SORT_ATTRIBUTE = "id";

    private int page = 0;
    private int size = DEFAULT_PAGE_SIZE;
    private Sort sort;

    public PageBuilder setPage(final int page) {
        this.page = page;
        return this;
    }

    public PageBuilder setSize(final Integer size) {
        this.size = Objects.requireNonNullElse(size, DEFAULT_PAGE_SIZE);
        return this;
    }

    public PageBuilder setSortByMultipleParams(final String sortBy, final String order) {
        final String[] sorters = sortBy.split(",");
        final String[] orders = order.split(",");
        final List<Sort.Order> ordersToSort = new ArrayList<>();

        int index = 0;
        for (String s : sorters) {
            Sort.Order newOrder;
            if (orders[index].equals("asc"))
                newOrder = new Sort.Order(Sort.Direction.ASC, s);
            else
                newOrder = new Sort.Order(Sort.Direction.DESC, s);
            ordersToSort.add(newOrder);
            index++;
        }
        this.sort = Sort.by(ordersToSort);
        return this;
    }

    public PageBuilder setSortBy(final String sortBy, final String order) {
        if (order != null && (order.equals("asc")))
            this.sort = Sort.by(sortBy).ascending();
        else
            this.sort = Sort.by(sortBy).descending();
        return this;
    }

    public Pageable build() {
        if (this.sort == null)
            setSortBy(DEFAULT_SORT_ATTRIBUTE, "desc");
        return PageRequest.of(this.page, this.size, this.sort);
    }
}
