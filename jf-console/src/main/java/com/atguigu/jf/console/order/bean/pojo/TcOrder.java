package com.atguigu.jf.console.order.bean.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TcOrder {
    private Long orderId;

    private String orderCode;

    private Integer orderType;

    private Long mchtId;

    private String mchtName;

    private Long itemId;

    private Integer itemType;

    private Long itemCode;

    private String itemName;

    private BigDecimal itemActPrice;

    private Long userId;

    private String userName;

    private String userNickname;

    private String loginName;

    private String userPhoneno;

    private Integer orderChannel;

    private Date orderTime;

    private Integer payType;

    private Integer payState;

    private Date payTime;

    private Integer orderItemNums;

    private BigDecimal goodsDealFee;

    private BigDecimal orderPayedFee;

    private Integer cancelRsnType;

    private String cancelReason;

    private Integer evaluationState;

    private Long evaluationId;

    private Long rcvAddrId;

    private String orderExtInfo1;

    private String orderExtInfo2;

    private String orderExtInfo3;

    private String orderExtInfo4;

    private String orderExtInfo5;

    private String orderExtInfo6;

    private String orderExtInfo7;

    private String orderExtInfo8;

    private String orderExtInfo9;

    private String orderExtInfo10;

    private Short dataState;

    private Long creator;

    private Date createTime;

    private Long modifyer;

    private Date modifyTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getMchtId() {
        return mchtId;
    }

    public void setMchtId(Long mchtId) {
        this.mchtId = mchtId;
    }

    public String getMchtName() {
        return mchtName;
    }

    public void setMchtName(String mchtName) {
        this.mchtName = mchtName == null ? null : mchtName.trim();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Long getItemCode() {
        return itemCode;
    }

    public void setItemCode(Long itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public BigDecimal getItemActPrice() {
        return itemActPrice;
    }

    public void setItemActPrice(BigDecimal itemActPrice) {
        this.itemActPrice = itemActPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getUserPhoneno() {
        return userPhoneno;
    }

    public void setUserPhoneno(String userPhoneno) {
        this.userPhoneno = userPhoneno == null ? null : userPhoneno.trim();
    }

    public Integer getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getOrderItemNums() {
        return orderItemNums;
    }

    public void setOrderItemNums(Integer orderItemNums) {
        this.orderItemNums = orderItemNums;
    }

    public BigDecimal getGoodsDealFee() {
        return goodsDealFee;
    }

    public void setGoodsDealFee(BigDecimal goodsDealFee) {
        this.goodsDealFee = goodsDealFee;
    }

    public BigDecimal getOrderPayedFee() {
        return orderPayedFee;
    }

    public void setOrderPayedFee(BigDecimal orderPayedFee) {
        this.orderPayedFee = orderPayedFee;
    }

    public Integer getCancelRsnType() {
        return cancelRsnType;
    }

    public void setCancelRsnType(Integer cancelRsnType) {
        this.cancelRsnType = cancelRsnType;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public Integer getEvaluationState() {
        return evaluationState;
    }

    public void setEvaluationState(Integer evaluationState) {
        this.evaluationState = evaluationState;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Long getRcvAddrId() {
        return rcvAddrId;
    }

    public void setRcvAddrId(Long rcvAddrId) {
        this.rcvAddrId = rcvAddrId;
    }

    public String getOrderExtInfo1() {
        return orderExtInfo1;
    }

    public void setOrderExtInfo1(String orderExtInfo1) {
        this.orderExtInfo1 = orderExtInfo1 == null ? null : orderExtInfo1.trim();
    }

    public String getOrderExtInfo2() {
        return orderExtInfo2;
    }

    public void setOrderExtInfo2(String orderExtInfo2) {
        this.orderExtInfo2 = orderExtInfo2 == null ? null : orderExtInfo2.trim();
    }

    public String getOrderExtInfo3() {
        return orderExtInfo3;
    }

    public void setOrderExtInfo3(String orderExtInfo3) {
        this.orderExtInfo3 = orderExtInfo3 == null ? null : orderExtInfo3.trim();
    }

    public String getOrderExtInfo4() {
        return orderExtInfo4;
    }

    public void setOrderExtInfo4(String orderExtInfo4) {
        this.orderExtInfo4 = orderExtInfo4 == null ? null : orderExtInfo4.trim();
    }

    public String getOrderExtInfo5() {
        return orderExtInfo5;
    }

    public void setOrderExtInfo5(String orderExtInfo5) {
        this.orderExtInfo5 = orderExtInfo5 == null ? null : orderExtInfo5.trim();
    }

    public String getOrderExtInfo6() {
        return orderExtInfo6;
    }

    public void setOrderExtInfo6(String orderExtInfo6) {
        this.orderExtInfo6 = orderExtInfo6 == null ? null : orderExtInfo6.trim();
    }

    public String getOrderExtInfo7() {
        return orderExtInfo7;
    }

    public void setOrderExtInfo7(String orderExtInfo7) {
        this.orderExtInfo7 = orderExtInfo7 == null ? null : orderExtInfo7.trim();
    }

    public String getOrderExtInfo8() {
        return orderExtInfo8;
    }

    public void setOrderExtInfo8(String orderExtInfo8) {
        this.orderExtInfo8 = orderExtInfo8 == null ? null : orderExtInfo8.trim();
    }

    public String getOrderExtInfo9() {
        return orderExtInfo9;
    }

    public void setOrderExtInfo9(String orderExtInfo9) {
        this.orderExtInfo9 = orderExtInfo9 == null ? null : orderExtInfo9.trim();
    }

    public String getOrderExtInfo10() {
        return orderExtInfo10;
    }

    public void setOrderExtInfo10(String orderExtInfo10) {
        this.orderExtInfo10 = orderExtInfo10 == null ? null : orderExtInfo10.trim();
    }

    public Short getDataState() {
        return dataState;
    }

    public void setDataState(Short dataState) {
        this.dataState = dataState;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyer() {
        return modifyer;
    }

    public void setModifyer(Long modifyer) {
        this.modifyer = modifyer;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}