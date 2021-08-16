package edu.cczu.tmall.admin.util;

/**
 * @description 分页工具类
 * @author iceorangeduxiaocheng@aliyun.com
 * @date 2021/8/15 16:52
 */
public class Page {

    private int start;                                  // 开始页数
    private int count;                                  // 每页显示记录条数
    private int total;                                  // 总记录条数
    private String param;                               // 参数(后续使用)

    private static final int DEFAULT_CONT = 5;          // 每页默认显示记录条数

    public Page (){
        count = DEFAULT_CONT;
    }

    public Page(int start, int count) {
        this();
        this.start = start;
        this.count = count;
    }

    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public String getParam() {
        return param;
    }
    public void setParam(String param) {
        this.param = param;
    }


    /**
     * 判断是否具有上一页
     * @return true：具有上一页 false：没有上一页
     */
    public boolean isHasPreviouse(){
        // 开始页start为0的时候，没有上一页
        if(start==0)
            return false;
        return true;
    }

    /**
     * 判断是否具有下一页
     * @return true：具有下一页 false：没有下一页
     */
    public boolean isHasNext(){
        // 开始页为最后一页的时候，没有下一页
        if(start==getLast())
            return false;
        return true;
    }

    /**
     * 计算最后一页的数值
     * @return 最后一页的数值
     */
    public int getLast(){
        int last;
        // 假设总数是50，是能够被5整除的，那么最后一页的开始就是45
        /*
            场景描述：如果总记录条数对每页显示记录条数求余
                ==0：表示可以整除，则最后一页的开始记录号就是总记录条数-每页显示记录条数
         */
        if (0 == total % count) {
            last = total - count;
        }
        // 假设总数是51，不能够被5整除的，那么最后一页的开始就是50
        /*
            场景描述：如果总记录条数对每页显示记录条数求余
               !=0：表示不可整除，则最后一页的开始记录号就是total-total % count
         */
        else {
            last = total - total % count;
        }
        last = last < 0 ? 0 : last;
        return last;
    }

    /**
     * @description 计算总页数
     * @return  总页数
     */
    public int getTotalPage(){
        int totalPage;
        // 假设总数是50，是能够被5整除的，那么就有10页
        if (0 == total % count) {
            totalPage = total / count;
        }
            // 假设总数是51，不能够被5整除的，那么就有11页
        else {
            totalPage = total / count + 1;
        }

        if(0==totalPage) {
            totalPage = 1;
        }
        return totalPage;
    }

    @Override
    public String toString() {
        return "Page {start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
                + ", getCount()=" + getCount() + ", isHasPreviouse()=" + isHasPreviouse() + ", isHasNext()="
                + isHasNext() + ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "}";
    }
}
