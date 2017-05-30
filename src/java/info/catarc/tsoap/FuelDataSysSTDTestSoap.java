
package info.catarc.tsoap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "FuelDataSysSTDTestSoap", targetNamespace = "http://tsoap.catarc.info/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface FuelDataSysSTDTestSoap {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HelloWorld", action = "http://tsoap.catarc.info/HelloWorld")
    @WebResult(name = "HelloWorldResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "HelloWorld", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.HelloWorld")
    @ResponseWrapper(localName = "HelloWorldResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.HelloWorldResponse")
    public String helloWorld();

    /**
     * 
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "CheckUser", action = "http://tsoap.catarc.info/CheckUser")
    @WebResult(name = "CheckUserResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "CheckUser", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.CheckUser")
    @ResponseWrapper(localName = "CheckUserResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.CheckUserResponse")
    public boolean checkUser(
        @WebParam(name = "userName", targetNamespace = "http://tsoap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param vehicleInfoList
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns info.catarc.tsoap.OperateResult
     */
    @WebMethod(operationName = "UploadFuelData", action = "http://tsoap.catarc.info/UploadFuelData")
    @WebResult(name = "UploadFuelDataResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "UploadFuelData", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.UploadFuelData")
    @ResponseWrapper(localName = "UploadFuelDataResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.UploadFuelDataResponse")
    public OperateResult uploadFuelData(
        @WebParam(name = "userName", targetNamespace = "http://tsoap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "vehicleInfoList", targetNamespace = "http://tsoap.catarc.info/")
        ArrayOfVehicleBasicInfo vehicleInfoList,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param vehicleInfoList
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns info.catarc.tsoap.OperateResult
     */
    @WebMethod(operationName = "UploadOverTime", action = "http://tsoap.catarc.info/UploadOverTime")
    @WebResult(name = "UploadOverTimeResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "UploadOverTime", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.UploadOverTime")
    @ResponseWrapper(localName = "UploadOverTimeResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.UploadOverTimeResponse")
    public OperateResult uploadOverTime(
        @WebParam(name = "userName", targetNamespace = "http://tsoap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "vehicleInfoList", targetNamespace = "http://tsoap.catarc.info/")
        ArrayOfVehicleBasicInfo vehicleInfoList,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param vehicleInfoList
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns info.catarc.tsoap.OperateResult
     */
    @WebMethod(operationName = "ApplyUpdate", action = "http://tsoap.catarc.info/ApplyUpdate")
    @WebResult(name = "ApplyUpdateResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "ApplyUpdate", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.ApplyUpdate")
    @ResponseWrapper(localName = "ApplyUpdateResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.ApplyUpdateResponse")
    public OperateResult applyUpdate(
        @WebParam(name = "userName", targetNamespace = "http://tsoap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "vehicleInfoList", targetNamespace = "http://tsoap.catarc.info/")
        ArrayOfVehicleBasicInfo vehicleInfoList,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param reason
     * @param userName
     * @param vinList
     * @param password
     * @param oKey
     * @return
     *     returns info.catarc.tsoap.OperateResult
     */
    @WebMethod(operationName = "ApplyDelete", action = "http://tsoap.catarc.info/ApplyDelete")
    @WebResult(name = "ApplyDeleteResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "ApplyDelete", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.ApplyDelete")
    @ResponseWrapper(localName = "ApplyDeleteResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.ApplyDeleteResponse")
    public OperateResult applyDelete(
        @WebParam(name = "userName", targetNamespace = "http://tsoap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "vinList", targetNamespace = "http://tsoap.catarc.info/")
        ArrayOfString vinList,
        @WebParam(name = "reason", targetNamespace = "http://tsoap.catarc.info/")
        String reason,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param startTime
     * @param clxh
     * @param pagesize
     * @param clzl
     * @param vin
     * @param userName
     * @param endTime
     * @param timeType
     * @param password
     * @param oKey
     * @param rllx
     * @param pageNum
     * @return
     *     returns info.catarc.tsoap.ArrayOfVehicleBasicInfo
     */
    @WebMethod(operationName = "QueryUploadedFuelData", action = "http://tsoap.catarc.info/QueryUploadedFuelData")
    @WebResult(name = "QueryUploadedFuelDataResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "QueryUploadedFuelData", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryUploadedFuelData")
    @ResponseWrapper(localName = "QueryUploadedFuelDataResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryUploadedFuelDataResponse")
    public ArrayOfVehicleBasicInfo queryUploadedFuelData(
        @WebParam(name = "userName", targetNamespace = "http://tsoap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "pageNum", targetNamespace = "http://tsoap.catarc.info/")
        int pageNum,
        @WebParam(name = "pagesize", targetNamespace = "http://tsoap.catarc.info/")
        int pagesize,
        @WebParam(name = "vin", targetNamespace = "http://tsoap.catarc.info/")
        String vin,
        @WebParam(name = "clxh", targetNamespace = "http://tsoap.catarc.info/")
        String clxh,
        @WebParam(name = "clzl", targetNamespace = "http://tsoap.catarc.info/")
        String clzl,
        @WebParam(name = "rllx", targetNamespace = "http://tsoap.catarc.info/")
        String rllx,
        @WebParam(name = "startTime", targetNamespace = "http://tsoap.catarc.info/")
        String startTime,
        @WebParam(name = "endTime", targetNamespace = "http://tsoap.catarc.info/")
        String endTime,
        @WebParam(name = "timeType", targetNamespace = "http://tsoap.catarc.info/")
        String timeType,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param startTime
     * @param clxh
     * @param pagesize
     * @param clzl
     * @param vin
     * @param userId
     * @param endTime
     * @param timeType
     * @param password
     * @param oKey
     * @param rllx
     * @param pageNum
     * @return
     *     returns info.catarc.tsoap.ArrayOfVehicleBasicInfo
     */
    @WebMethod(operationName = "QueryApplyUpLoadOT", action = "http://tsoap.catarc.info/QueryApplyUpLoadOT")
    @WebResult(name = "QueryApplyUpLoadOTResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "QueryApplyUpLoadOT", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryApplyUpLoadOT")
    @ResponseWrapper(localName = "QueryApplyUpLoadOTResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryApplyUpLoadOTResponse")
    public ArrayOfVehicleBasicInfo queryApplyUpLoadOT(
        @WebParam(name = "userId", targetNamespace = "http://tsoap.catarc.info/")
        String userId,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "pageNum", targetNamespace = "http://tsoap.catarc.info/")
        int pageNum,
        @WebParam(name = "pagesize", targetNamespace = "http://tsoap.catarc.info/")
        int pagesize,
        @WebParam(name = "vin", targetNamespace = "http://tsoap.catarc.info/")
        String vin,
        @WebParam(name = "clxh", targetNamespace = "http://tsoap.catarc.info/")
        String clxh,
        @WebParam(name = "clzl", targetNamespace = "http://tsoap.catarc.info/")
        String clzl,
        @WebParam(name = "rllx", targetNamespace = "http://tsoap.catarc.info/")
        String rllx,
        @WebParam(name = "startTime", targetNamespace = "http://tsoap.catarc.info/")
        String startTime,
        @WebParam(name = "endTime", targetNamespace = "http://tsoap.catarc.info/")
        String endTime,
        @WebParam(name = "timeType", targetNamespace = "http://tsoap.catarc.info/")
        String timeType,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param startTime
     * @param clxh
     * @param pagesize
     * @param clzl
     * @param vin
     * @param userName
     * @param endTime
     * @param timeType
     * @param password
     * @param oKey
     * @param rllx
     * @param pageNum
     * @return
     *     returns info.catarc.tsoap.ArrayOfVehicleBasicInfo
     */
    @WebMethod(operationName = "QueryApplyDelInfo", action = "http://tsoap.catarc.info/QueryApplyDelInfo")
    @WebResult(name = "QueryApplyDelInfoResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "QueryApplyDelInfo", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryApplyDelInfo")
    @ResponseWrapper(localName = "QueryApplyDelInfoResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryApplyDelInfoResponse")
    public ArrayOfVehicleBasicInfo queryApplyDelInfo(
        @WebParam(name = "userName", targetNamespace = "http://tsoap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "pageNum", targetNamespace = "http://tsoap.catarc.info/")
        int pageNum,
        @WebParam(name = "pagesize", targetNamespace = "http://tsoap.catarc.info/")
        int pagesize,
        @WebParam(name = "vin", targetNamespace = "http://tsoap.catarc.info/")
        String vin,
        @WebParam(name = "clxh", targetNamespace = "http://tsoap.catarc.info/")
        String clxh,
        @WebParam(name = "clzl", targetNamespace = "http://tsoap.catarc.info/")
        String clzl,
        @WebParam(name = "rllx", targetNamespace = "http://tsoap.catarc.info/")
        String rllx,
        @WebParam(name = "startTime", targetNamespace = "http://tsoap.catarc.info/")
        String startTime,
        @WebParam(name = "endTime", targetNamespace = "http://tsoap.catarc.info/")
        String endTime,
        @WebParam(name = "timeType", targetNamespace = "http://tsoap.catarc.info/")
        String timeType,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param startTime
     * @param clxh
     * @param pagesize
     * @param clzl
     * @param vin
     * @param userName
     * @param endTime
     * @param timeType
     * @param password
     * @param oKey
     * @param rllx
     * @param pageNum
     * @return
     *     returns info.catarc.tsoap.ArrayOfVehicleBasicInfo
     */
    @WebMethod(operationName = "QueryApplyUpdateInfo", action = "http://tsoap.catarc.info/QueryApplyUpdateInfo")
    @WebResult(name = "QueryApplyUpdateInfoResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "QueryApplyUpdateInfo", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryApplyUpdateInfo")
    @ResponseWrapper(localName = "QueryApplyUpdateInfoResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryApplyUpdateInfoResponse")
    public ArrayOfVehicleBasicInfo queryApplyUpdateInfo(
        @WebParam(name = "userName", targetNamespace = "http://tsoap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "pageNum", targetNamespace = "http://tsoap.catarc.info/")
        int pageNum,
        @WebParam(name = "pagesize", targetNamespace = "http://tsoap.catarc.info/")
        int pagesize,
        @WebParam(name = "vin", targetNamespace = "http://tsoap.catarc.info/")
        String vin,
        @WebParam(name = "clxh", targetNamespace = "http://tsoap.catarc.info/")
        String clxh,
        @WebParam(name = "clzl", targetNamespace = "http://tsoap.catarc.info/")
        String clzl,
        @WebParam(name = "rllx", targetNamespace = "http://tsoap.catarc.info/")
        String rllx,
        @WebParam(name = "startTime", targetNamespace = "http://tsoap.catarc.info/")
        String startTime,
        @WebParam(name = "endTime", targetNamespace = "http://tsoap.catarc.info/")
        String endTime,
        @WebParam(name = "timeType", targetNamespace = "http://tsoap.catarc.info/")
        String timeType,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns info.catarc.tsoap.ArrayOfRllxParam
     */
    @WebMethod(operationName = "QueryRllxParamData", action = "http://tsoap.catarc.info/QueryRllxParamData")
    @WebResult(name = "QueryRllxParamDataResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "QueryRllxParamData", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryRllxParamData")
    @ResponseWrapper(localName = "QueryRllxParamDataResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryRllxParamDataResponse")
    public ArrayOfRllxParam queryRllxParamData(
        @WebParam(name = "userName", targetNamespace = "http://tsoap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "QueryParamVersion", action = "http://tsoap.catarc.info/QueryParamVersion")
    @WebResult(name = "QueryParamVersionResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "QueryParamVersion", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryParamVersion")
    @ResponseWrapper(localName = "QueryParamVersionResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryParamVersionResponse")
    public String queryParamVersion(
        @WebParam(name = "userName", targetNamespace = "http://tsoap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns info.catarc.tsoap.QyInfo
     */
    @WebMethod(operationName = "QueryQyInfo", action = "http://tsoap.catarc.info/QueryQyInfo")
    @WebResult(name = "QueryQyInfoResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "QueryQyInfo", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryQyInfo")
    @ResponseWrapper(localName = "QueryQyInfoResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryQyInfoResponse")
    public QyInfo queryQyInfo(
        @WebParam(name = "userName", targetNamespace = "http://tsoap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userId
     * @param password
     * @param oKey
     * @return
     *     returns info.catarc.tsoap.ArrayOfString
     */
    @WebMethod(operationName = "QueryHolidayData", action = "http://tsoap.catarc.info/QueryHolidayData")
    @WebResult(name = "QueryHolidayDataResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "QueryHolidayData", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryHolidayData")
    @ResponseWrapper(localName = "QueryHolidayDataResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryHolidayDataResponse")
    public ArrayOfString queryHolidayData(
        @WebParam(name = "userId", targetNamespace = "http://tsoap.catarc.info/")
        String userId,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userId
     * @param password
     * @param oKey
     * @return
     *     returns int
     */
    @WebMethod(operationName = "QueryUploadTimeConstrain", action = "http://tsoap.catarc.info/QueryUploadTimeConstrain")
    @WebResult(name = "QueryUploadTimeConstrainResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "QueryUploadTimeConstrain", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryUploadTimeConstrain")
    @ResponseWrapper(localName = "QueryUploadTimeConstrainResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryUploadTimeConstrainResponse")
    public int queryUploadTimeConstrain(
        @WebParam(name = "userId", targetNamespace = "http://tsoap.catarc.info/")
        String userId,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userId
     * @param password
     * @param oKey
     * @param vinArray
     * @return
     *     returns info.catarc.tsoap.ArrayOfString
     */
    @WebMethod(operationName = "QueryVidByVin", action = "http://tsoap.catarc.info/QueryVidByVin")
    @WebResult(name = "QueryVidByVinResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "QueryVidByVin", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryVidByVin")
    @ResponseWrapper(localName = "QueryVidByVinResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryVidByVinResponse")
    public ArrayOfString queryVidByVin(
        @WebParam(name = "userId", targetNamespace = "http://tsoap.catarc.info/")
        String userId,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "vinArray", targetNamespace = "http://tsoap.catarc.info/")
        ArrayOfString vinArray,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userId
     * @param password
     * @param oKey
     * @return
     *     returns info.catarc.tsoap.ArrayOfString
     */
    @WebMethod(operationName = "QueryNotice", action = "http://tsoap.catarc.info/QueryNotice")
    @WebResult(name = "QueryNoticeResult", targetNamespace = "http://tsoap.catarc.info/")
    @RequestWrapper(localName = "QueryNotice", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryNotice")
    @ResponseWrapper(localName = "QueryNoticeResponse", targetNamespace = "http://tsoap.catarc.info/", className = "info.catarc.tsoap.QueryNoticeResponse")
    public ArrayOfString queryNotice(
        @WebParam(name = "userId", targetNamespace = "http://tsoap.catarc.info/")
        String userId,
        @WebParam(name = "password", targetNamespace = "http://tsoap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://tsoap.catarc.info/")
        String oKey);

}
