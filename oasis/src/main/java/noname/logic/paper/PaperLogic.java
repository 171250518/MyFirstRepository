package noname.logic.paper;


import noname.VO.Response;

/**
 * 论文逻辑接口
 *
 * @author wch
 * @date 2020/3/6
 */

public interface PaperLogic {
    /**
     * 得到发表论文最多的年份
     * @return Response对象
     */
    Response showMostPaperYear();
    /**
     * 得到发表论文最多的机构
     * @return Response对象
     */
    Response showMostPaperConf();
}
