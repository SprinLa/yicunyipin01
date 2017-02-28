package com.yicunyipin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="tb_member")
public class Member {
	private int id;
	private String userName;//�û���,��¼����
	private String password;//��¼����
	private int status;//0-δ�ύ��Ա��Ϣ��1-δ�ύ��Ʒ 2-�ύ��Ϣ���
	private String productName;//��Ʒ��������
	private String memberName;//��Ա����
	private String legal;//��ҵ����
	private String contactName;//��Ա��ϵ��
	private int type;//�����Ա����,��1-��ͨ��Ա��2-VIP��Ա
	private String address;//���ҵ�ַ
	private String phoneNum;//��ϵ��ʽ�ֻ���
	private String telNum;//�̻�
	private String factoryInfo;//������Ϣ
	private int verified;//�������1-���ͨ����2-���δͨ����0-δ���
	private String verifiedReason;//���δͨ����ԭ��
	private int isDelete;//�Ƿ�ɾ����0-δɾ����1-ɾ��
	private String deleteReason;//ɾ��ԭ��
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="user_name",length=20,nullable=false)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(nullable=false,length=50)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="product_name",length=255)
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(name="member_name",length=255)
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getLegal() {
		return legal;
	}
	public void setLegal(String legal) {
		this.legal = legal;
	}
	@Column(name="contact_name",length=255)
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="phone_num",length=20)
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Column(name="tel_num",length=20)
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	@Column(name="factory_info",columnDefinition="TEXT")
	public String getFactoryInfo() {
		return factoryInfo;
	}
	public void setFactoryInfo(String factoryInfo) {
		this.factoryInfo = factoryInfo;
	}
	public int getVerified() {
		return verified;
	}
	public void setVerified(int verified) {
		this.verified = verified;
	}
	@Column(name="verified_reason",columnDefinition="TEXT")
	public String getVerifiedReason() {
		return verifiedReason;
	}
	public void setVerifiedReason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name="delete_reason",columnDefinition="TEXT")
	public String getDeleteReason() {
		return deleteReason;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	
}
