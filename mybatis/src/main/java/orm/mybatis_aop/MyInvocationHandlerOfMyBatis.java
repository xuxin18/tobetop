package orm.mybatis_aop;

import orm.annotation.ExtInsert;
import orm.annotation.ExtParam;
import orm.utils.JDBCUtils;
import sql.SQLUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuxin
 * @version v1.0
 * @project to_be_top
 * @package orm.mybatis_aop
 * @date 24 15:${MIMUTE}
 * @modified
 *
 * 通过反射使用动态代理技术来 拦截接口的方法
 */
public class MyInvocationHandlerOfMyBatis implements InvocationHandler {

    //目标对象
    private Object object;

    public MyInvocationHandlerOfMyBatis(Object object) {
        this.object = object;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("使用动态代理技术拦截接口方法");
        //@ExtInsert封装
        //1.判断方法上是否存在 @ExtInsert
        ExtInsert extInsertAnno = method.getDeclaredAnnotation(ExtInsert.class);
        if (extInsertAnno != null){
            //2.从注解中获取sql语句
            String insertSql = extInsertAnno.value();
            System.out.println(insertSql);

            //3.获取方法的参数与sql参数进行匹配
            //定义一个Map集合，key 为 @ExtParam 的 value值，value 为形参
            ConcurrentHashMap<Object, Object> paramsMap = new ConcurrentHashMap<>();
            //获取方法上的参数
            Parameter[] parameters = method.getParameters();
            for (int i=0; i < parameters.length; i++){
                Parameter parameter = parameters[i];
                ExtParam extParamAnno = parameter.getDeclaredAnnotation(ExtParam.class);
                if (extParamAnno != null){
                    String paramName = extParamAnno.value();
                    Object paramValue = args[i];
                    System.out.println(paramName + "," + paramValue);
                    paramsMap.put(paramName, paramValue);
                }
            }

            //存放sql语句的参数 --- 参数绑定过程
            List<Object> sqlParams = new ArrayList<>();
            String[] sqlInsertParameter = SQLUtils.sqlInsertParameter(insertSql);
            for (String paramName : sqlInsertParameter){
                Object paramValue = paramsMap.get(paramName);
                sqlParams.add(paramValue);
            }

            //4.替换参数为 ?
            String newSql = SQLUtils.parameQuestion(insertSql, sqlInsertParameter);
            System.out.println(newSql);

            //5.调用jdbc底层代码执行语句
            JDBCUtils.insert(newSql, false, sqlParams);
        }




        return 1;
    }
}
