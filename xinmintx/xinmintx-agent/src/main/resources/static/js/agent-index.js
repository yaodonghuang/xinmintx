//添加代理
function addAgency() {
    location.href="/agent/agent/addAgent";
}
//添加商户
function addCommercialTenant() {
    location.href="/agent/merchant/addMerchant";
}
//添加新民金卡
function addSilverCard() {
    location.href="/agent/silver/addSilver";
}
//添加员工
function addStaff() {
    location.href="/agent/staff/addStaff";
}
//添加公司
function addCompany() {
    location.href="/agent/company/addCompany";
}
//添加合伙人
function addPartner() {
    location.href="/agent/partner/addPartner";
}
//查看分公司
function listCompany() {
    location.href="/agent/company/listCompany";
}
//查看合伙人
function listPartner() {
    location.href="/agent/partner/listPartner";
}
//查看代理
function listAgent() {
    location.href="/agent/agent/listAgent";
}
//查看新民金卡
function listSilver() {
    location.href="/agent/silver/listSilver";
}
//查看员工
function listStaff() {
    location.href="/agent/staff/listStaff";
}
//查看提货点
function listPickUp() {
    location.href="/agent/pickup/listPickUp";
}
//查看商户
function listMerchant() {
    location.href="/agent/merchant/listMerchant";
}
//设置跳转
function setIndex() {
    location.href="/agent/setIndex/setIndex";
}
//资料包跳转
function dataGram() {
    location.href="/agent/dataGram/dataGram";
}

//设置密码跳转
function pwd() {
    location.href="/agent/pwd/pwd";
}
//工厂跳转
function addPlant() {
    location.href="/agent/plant/addPlant";
}
//地区跳转
function addArea() {
    location.href="/agent/area/addArea";
}
//商品采集列表
function goShopping() {
    location.href="/agent/shopping/goShopping";
}
//高级合伙人跳转
function shareholder() {
    location.href="/agent/shareholder/jumpShareholder";
}
//高级合伙人跳转
function listTeam() {
    location.href="/agent/shareholder/listTeam";
}
//提现功能
function deposit() {
    $.ajax({
        url: "/agent/bankCard/selectBankCard",
        method: "POST",
        contentType: "application/x-www-form-urlencoded",
        data: $("#agent_form").serialize(),
        success: function (res) {
            if(!res){
                var r = confirm("我还没有银行卡,现在去绑定！");
                if (r == true) {
                    location.href="/agent/bankCard/jumpCard" //绑定银行卡
                } else {
                    return;
                };
            }else {
                location.href="/agent/bankCard/jumpDeposit" //提现页面
            }
        }
    });
}
//商品采集列表
function commodities() {
    location.href="/agent/shopping/jumpAddShopping";
}
//商品采购市场
function goProcurementMarket() {
    location.href="/agent/procurementMarket/jumpProcurementMarket";
}
//绑定银行卡
function bankCard() {
    location.href="/agent/bankCard/jumpCard";
}
//合伙人我的团队
function partnerTeamList() {
    location.href="/agent/agent/partnerTeamList";
}
function onSale() {
    location.href="/agent/procurementMarket/jumpOnSale";
}
function aaa() {
    location.href="/agent/bankCard/111";
}
function balanceStatement() {
    location.href="/user/detail/queryDetail";
}
function myClass() {
    location.href="/agent/teacher/myClass";
}
//增加提货点
function addPickUp() {
    location.href="/agent/pickup/jumpPick";
}