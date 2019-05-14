package com.liwj.szpd.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageResult<T>  implements Serializable {
    private static final long serialVersionUID = 2696109518770817050L;
    private int pgCt;
    private int pgSz;
    private int end;
    private long total;
    private List<T> datas;

    public PageResult(int pgCt, int pgSz) {
        this(pgCt, pgSz, 0);
    }

    public PageResult(int pgCt, int pgSz, long total) {
        this.datas = new ArrayList();
        this.pgCt = pgCt < 1 ? 1 : pgCt;
        this.pgSz = pgSz < 1 ? 10 : pgSz;
        this.total = total;
        this.end = ((int)total + pgSz - 1) / pgSz;
    }

    public PageResult(int pgCt, int pgSz, long total, List<T> datas) {
        this(pgCt, pgSz, total);
        this.datas = datas;
    }

    public int getPgCt() {
        return this.pgCt;
    }

    public int getPgSz() {
        return this.pgSz;
    }

    public int getEnd() {
        return this.end;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return this.datas;
    }

    public void setPgCt(int pgCt) {
        this.pgCt = pgCt;
    }

    public void setPgSz(int pgSz) {
        this.pgSz = pgSz;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageResult)) {
            return false;
        } else {
            PageResult<?> other = (PageResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getPgCt() != other.getPgCt()) {
                return false;
            } else if (this.getPgSz() != other.getPgSz()) {
                return false;
            } else if (this.getEnd() != other.getEnd()) {
                return false;
            } else if (this.getTotal() != other.getTotal()) {
                return false;
            } else {
                Object this$datas = this.getDatas();
                Object other$datas = other.getDatas();
                if (this$datas == null) {
                    if (other$datas != null) {
                        return false;
                    }
                } else if (!this$datas.equals(other$datas)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageResult;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "PageResult(pgCt=" + this.getPgCt() + ", pgSz=" + this.getPgSz() + ", end=" + this.getEnd() + ", total=" + this.getTotal() + ", datas=" + this.getDatas() + ")";
    }
}
