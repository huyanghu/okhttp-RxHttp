package rxhttp.wrapper.callback;


import java.io.IOException;
import java.lang.reflect.Type;

import io.reactivex.annotations.NonNull;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rxhttp.wrapper.converter.GsonConverter;

/**
 * User: ljx
 * Date: 2019-11-19
 * Time: 22:54
 */
public interface IConverter {

    /**
     * 请求结束后拿到 ResponseBody 转 对象
     *
     * @param body            ResponseBody
     * @param type            对象类型
     * @param onResultDecoder 是否需要对结果进行解码/解密，可根据此字段判断,
     *                        可参考{@link GsonConverter#convert(ResponseBody, Type, boolean)}
     * @param <T>             T
     * @return T
     * @throws IOException 转换失败异常
     */
    @NonNull
    <T> T convert(@NonNull ResponseBody body, @NonNull Type type, boolean onResultDecoder) throws IOException;

    /**
     * 对象转 RequestBody,目前(RxHttp 1.3.1版本)发送{application/json; charset=utf-8}类型请求前，将会调用此方法
     *
     * @param value T
     * @param <T> T
     * @return RequestBody
     * @throws IOException 转换失败异常
     */
    default <T> RequestBody convert(T value) throws IOException {
        return RequestBody.create(null, new byte[0]);
    }
}
