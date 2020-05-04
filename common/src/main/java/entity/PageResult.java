package entity;

import java.util.List;

public class PageResult<T> {

    public int getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(int totalPages) {
        TotalPages = totalPages;
    }

    private int TotalPages;



    public Long getTotalElements() {
        return TotalElements;
    }

    public void setTotalElements(Long totalElements) {
        TotalElements = totalElements;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    private Long TotalElements;
    private List<T> rows;

    public PageResult(Long TotalElements, int TotalPages, List<T> rows) {
        super();
        this.TotalElements = TotalElements;
        this.TotalPages = TotalPages;
        this.rows = rows;
    }


}
