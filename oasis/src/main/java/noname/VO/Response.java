package noname.VO;

/**
 * 请求响应类
 * @author cdx
 * @date 2020/02/27 10:38
 *
 *第一次修改
 * @author wch
 * @date 2020/2/29
 *
 * 第二次修改
 * @author ffj
 * @date 2020/2/29
 */
public class Response {
    /**
     * 请求是否成功
     */
    private Boolean success;

    /**
     * 响应信息
     */
    private String msg;
    /**
     * 内容
     */
    private Object content;


    public Response(){
    }

    public Response(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public Object getContent() {
        return content;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public void setMessage(String message) {
        this.msg = message;
    }
    public void setContent(Object content){
        this.content = content;
    }

    public static Response buildSuccess(){
        Response response=new Response();
        response.setSuccess(true);
        return response;
    }

    public static Response buildSuccess(Object content){
        Response response=new Response();
        response.setSuccess(true);
        response.setContent(content);
        return response;
    }

    public static Response buildFailure(String message){
        Response response=new Response();
        response.setSuccess(false);
        response.setMessage(message);
        System.out.println(message);
        return response;
    }


}
