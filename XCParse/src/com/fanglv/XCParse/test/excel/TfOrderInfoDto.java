package com.fanglv.XCParse.test.excel;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.fanglv.XCParse.data.DataInstanceInterface;

/**
 * 淘房中国订单信息Dto
 *
 */
public class TfOrderInfoDto implements DataInstanceInterface{

    private String orderNo;    //订单编号
    private String buyerNickName;//买家会员名称
    private String buyerAlipayAccount;//买家支付宝账�?
    private String payAmount;//买家实际支付金额
    private String payJifen;//卖家实际支付积分
    private String orderStatus;//订单状�?
    private String buyerComment;//买家留言
    private String phone;//买家手机�?
    private Date createdTime;//订单创建时间
    private Date paidTime;//订单付款时间
    private String skuTitle;//众筹标题
    private Integer goodType;//宝贝种类
    private String orderComment;//订单备注
    private Integer goodNumber;//宝贝总数�?
    private Integer dpID; //店铺id
    private Integer dpName;//店铺名称
    private boolean isPhoneOrder; // 是否是手机订�? Excel中，空白表示 PC�?
    private String buildingName;
    private Integer buildingId;
    private String sellerName;
    private Integer sellerId;

    public Integer getGoodType() {
		return goodType;
	}

	public void setGoodType(Integer goodType) {
		this.goodType = goodType;
	}

	public Integer getGoodNumber() {
		return goodNumber;
	}

	public void setGoodNumber(Integer goodNumber) {
		this.goodNumber = goodNumber;
	}

	public Integer getDpID() {
		return dpID;
	}

	public void setDpID(Integer dpID) {
		this.dpID = dpID;
	}

	public Integer getDpName() {
		return dpName;
	}

	public void setDpName(Integer dpName) {
		this.dpName = dpName;
	}

	public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBuyerNickName() {
        return buyerNickName;
    }

    public void setBuyerNickName(String buyerNickName) {
        this.buyerNickName = buyerNickName;
    }

    public String getBuyerAlipayAccount() {
        return buyerAlipayAccount;
    }

    public void setBuyerAlipayAccount(String buyerAlipayAccount) {
        this.buyerAlipayAccount = buyerAlipayAccount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayJifen() {
        return payJifen;
    }

    public void setPayJifen(String payJifen) {
        this.payJifen = payJifen;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBuyerComment() {
        return buyerComment;
    }

    public void setBuyerComment(String buyerComment) {
        this.buyerComment = buyerComment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public String getSkuTitle() {
        return skuTitle;
    }

    public void setSkuTitle(String skuTitle) {
        this.skuTitle = skuTitle;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public boolean isPhoneOrder() {
        return isPhoneOrder;
    }

    public void setPhoneOrder(boolean isPhoneOrder) {
        this.isPhoneOrder = isPhoneOrder;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

	@Override
	public LinkedHashMap<String, Boolean> getDataName() {

		LinkedHashMap<String, Boolean> map = new LinkedHashMap<String, Boolean>();
		//map.put("userID", false);
		map.put("orderNo", false);
		map.put("buyerNickName", false);
		map.put("buyerAlipayAccount", false);
		map.put("payAmount", false);
		map.put("payJifen", false);
		map.put("orderStatus", false);
		map.put("buyerComment", false);
		map.put("phone", false);
		map.put("createdTime", false);
		map.put("paidTime", false);
		map.put("skuTitle", false);
		map.put("goodType", false);
		map.put("orderComment", false);
		map.put("goodNumber", false);
		map.put("dpID", false);
		map.put("dpName", false);
		map.put("isPhoneOrder", false);
		return map;
	}
	/**
    private Integer goodNumber;//宝贝总数�?
    private Integer dpID; //店铺id
    private Integer dpName;//店铺名称
    private boolean isPhoneOrder; // 是否是手机订�? Excel中，空白表示 PC�?
	 */
	@Override
	public String toString() {
		return "TfOrderInfoDto [orderNo=" + orderNo + ", buyerNickName="
				+ buyerNickName + ", buyerAlipayAccount=" + buyerAlipayAccount
				+ "]";
	}
}
