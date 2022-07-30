//用于百度文本审核的

package com.web.xducatserver.TextCensor;
import com.web.xducatserver.TextCensor.HttpUtil;
import com.web.xducatserver.TextCensor.AuthService;
import org.json.JSONException;
import org.json.JSONObject;

public class TextCensor {
    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
//    其中参数text是待审核的文本
    public static String TextCensor(String text) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/text_censor/v2/user_defined";
        try {
            String param = "text=" + text;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
//            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    测试用
    public static void main(String[] args) throws JSONException {

//      TextCensor.TextCensor();

        String result = "{\"conclusion\":\"不合规\",\"log_id\":16532948121518774,\"data\":[{\"msg\":\"存在文本色情不合规\",\"conclusion\":\"不合规\",\"hits\":[{\"wordHitPositions\":[{\"positions\":[[28,29],[64,65]],\"label\":\"200100\",\"keyword\":\"淫荡\"}],\"probability\":1.0,\"datasetName\":\"百度默认文本反作弊库\",\"words\":[\"淫荡\"],\"modelHitPositions\":[[0,165,0.9571]]}],\"subType\":2,\"conclusionType\":2,\"type\":12}],\"isHitMd5\":false,\"conclusionType\":2}";
        JSONObject jsonObject = new JSONObject(result);
        System.out.print(jsonObject.getString("conclusion"));

    }
}
