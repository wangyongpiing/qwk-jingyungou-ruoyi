package com.ruoyi.qwk.domain;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邀请用户关系对象 invite_subordinate
 *
 * @author cjf
 * @date 2023-09-27
 */
public class InvitationAssistanceRecord  extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String invitationPhone;

    private String avatar;

    private String remark;

    private String zfbPicture;

    public String getInvitationPhone() {
        return invitationPhone;
    }

    public void setInvitationPhone(String invitationPhone) {
        this.invitationPhone = invitationPhone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZfbPicture() {
        return zfbPicture;
    }

    public void setZfbPicture(String zfbPicture) {
        this.zfbPicture = zfbPicture;
    }

    public InvitationAssistanceRecord(){

    }

    public InvitationAssistanceRecord(String invitationPhone,String avatar,String remark,String zfbPicture){
        this.invitationPhone = invitationPhone;
        this.avatar = avatar;
        this.remark = remark;
        this.zfbPicture = zfbPicture;
    }
}
