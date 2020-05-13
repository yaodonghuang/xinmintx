/**
 * author:zhangxiaowu
 * date:2016年9月11日
 * version:1.0
 * email:uf_zhangxiaowu@163.com
 */

var alreadySetSkuVals = {};//已经设置的SKU值数据
$(function () {
    //sku属性发生改变时,进行表格创建
    $(document).on("change", '.sku_value', function () {
        getAlreadySetSkuVals();//获取已经设置的SKU值

        var b = true;
        var skuTypeArr = [];//存放SKU类型的数组
        var totalRow = 1;//总行数
        //获取元素类型
        $(".SKU_TYPE").each(function () {
            //SKU类型节点
            var skuTypeNode = $(this).children("li");
            var skuTypeObj = {};//sku类型对象
            //SKU属性类型标题
            skuTypeObj.skuTypeTitle = $(skuTypeNode).attr("sku-type-name");
            //SKU属性类型主键
            var propid = $(skuTypeNode).attr("propid");
            skuTypeObj.skuTypeKey = propid;
            //是否是必选SKU 0：不是；1：是；
            var is_required = $(skuTypeNode).attr("is_required");
            skuValueArr = [];//存放SKU值得数组
            //SKU相对应的节点
            var skuValNode = $(this).next();
            //获取SKU值
            var skuValCheckBoxs = $(skuValNode).find("input[type='checkbox'][class*='sku_value']");
            var checkedNodeLen = 0;//选中的SKU节点的个数
            $(skuValCheckBoxs).each(function () {
                if ($(this).is(":checked")) {
                    var skuValObj = {};//SKU值对象
                    skuValObj.skuValueTitle = $(this).val();//SKU值名称
                    skuValObj.skuValueId = $(this).attr("propvalid");//SKU值主键
                    skuValObj.skuPropId = $(this).attr("propid");//SKU类型主键
                    skuValueArr.push(skuValObj);
                    checkedNodeLen++;
                }
            });
            if (is_required && "1" == is_required) {//必选sku
                if (checkedNodeLen <= 0) {//有必选的SKU仍然没有选中
                    b = false;
                    return false;//直接返回
                }
            }
            if (skuValueArr && skuValueArr.length > 0) {
                totalRow = totalRow * skuValueArr.length;
                skuTypeObj.skuValues = skuValueArr;//sku值数组
                skuTypeObj.skuValueLen = skuValueArr.length;//sku值长度
                skuTypeArr.push(skuTypeObj);//保存进数组中
            }
        });
        var SKUTableDom = "";//sku表格数据
        //开始创建行
        if (b) {//必选的SKU属性已经都选中了

//			//调整顺序(少的在前面,多的在后面)
//			skuTypeArr.sort(function(skuType1,skuType2){
//				return (skuType1.skuValueLen - skuType2.skuValueLen)
//			});
//			
            SKUTableDom += "<table class='skuTable'><tr>";
            //创建表头
            for (var t = 0; t < skuTypeArr.length; t++) {
                SKUTableDom += '<th>' + skuTypeArr[t].skuTypeTitle + '</th>';
            }
            SKUTableDom += '<th>价格</th><th>库存</th><th>重量</th><th>商品编号</th><th>代理价</th><th>采购价</th><th>e卡价</th><th>金卡价</th><th>图片</th>';
            SKUTableDom += "</tr>";
            //循环处理表体
            for (var i = 0; i < totalRow; i++) {//总共需要创建多少行
                var currRowDoms = "";
                var rowCount = 1;//记录行数
                var propvalidArr = [];//记录SKU值主键
                var propIdArr = [];//属性类型主键
                var propvalnameArr = [];//记录SKU值标题
                var propNameArr = [];//属性类型标题
                for (var j = 0; j < skuTypeArr.length; j++) {//sku列
                    var skuValues = skuTypeArr[j].skuValues;//SKU值数组
                    var skuValueLen = skuValues.length;//sku值长度
                    rowCount = (rowCount * skuValueLen);//目前的生成的总行数
                    var anInterBankNum = (totalRow / rowCount);//跨行数
                    var point = ((i / anInterBankNum) % skuValueLen);
                    propNameArr.push(skuTypeArr[j].skuTypeTitle);
                    if (0 == (i % anInterBankNum)) {//需要创建td
                        currRowDoms += '<td rowspan=' + anInterBankNum + '>' + skuValues[point].skuValueTitle + '</td>';
                        propvalidArr.push(skuValues[point].skuValueId);
                        propIdArr.push(skuValues[point].skuPropId);
                        propvalnameArr.push(skuValues[point].skuValueTitle);
                    } else {
                        //当前单元格为跨行
                        propvalidArr.push(skuValues[parseInt(point)].skuValueId);
                        propIdArr.push(skuValues[parseInt(point)].skuPropId);
                        propvalnameArr.push(skuValues[parseInt(point)].skuValueTitle);
                    }
                }
//				
//				//进行排序(主键小的在前,大的在后),注意:适用于数值类型的主键
//				propvalidArr.sort(function(provids1,propvids2){
//					return (provids1 - propvids2)
//				});
                var propvalids = propvalidArr.toString()
                var alreadySetSkuPrice = "";//已经设置的SKU价格
                var alreadySetSkuStock = "";//已经设置的SKU库存
                var alreadySetGoodsWeight = "";//已经设置的SKU重量
                var alreadySettingSkuId = "";//已经设置商品编号
                var alreadySetSkuPhoto = "";//已经设置商品图片
                var alreadySettingAgentPrice = "";//已经设置代理价
                var alreadySettingProcurementPrice = "";//已经设置采购价
                var alreadySettingEPrice = "";//已经设置e卡价
                var alreadySettingGlodPrice = "";//已经设置金卡价
                //赋值
                if (alreadySetSkuVals) {
                    var currGroupSkuVal = alreadySetSkuVals[propvalids];//当前这组SKU值
                    if (currGroupSkuVal) {
                        alreadySetSkuPrice = currGroupSkuVal.skuPrice;
                        alreadySetSkuStock = currGroupSkuVal.skuStock;
                        alreadySetGoodsWeight = currGroupSkuVal.goodsWeight;
                        alreadySettingSkuId = currGroupSkuVal.skuId;
                        alreadySetSkuPhoto = currGroupSkuVal.photo;
                        alreadySettingAgentPrice = currGroupSkuVal.agentPrice;
                        alreadySettingProcurementPrice = currGroupSkuVal.procurementPrice;
                        alreadySettingEPrice = currGroupSkuVal.procurementEPrice;
                        alreadySettingGlodPrice = currGroupSkuVal.procurementGlodPrice
                    }
                }
                //console.log(propvalids);
                SKUTableDom += '<tr propvalids=\'' + propvalids + '\' propids=\'' + propIdArr.toString() + '\' propvalnames=\'' + propvalnameArr.join(";") + '\'  propnames=\'' + propNameArr.join(";") + '\' class="sku_table_tr">' + currRowDoms + '<td><input type="text" class="setting_sku_price" value="' + alreadySetSkuPrice + '"/></td>' +
                    '<td><input type="text" class="setting_sku_stock" value="' + alreadySetSkuStock + '"/></td>' +
                    '<td><input type="text" class="setting_sku_goodsWeight" value="' + alreadySetGoodsWeight + '"/></td>' +
                    '<td><input type="text" class="setting_sku_id" value="' + alreadySettingSkuId + '"/></td>' +
                    '<td><input type="text" class="setting_sku_agentPrice" value="' + alreadySettingAgentPrice + '"/></td>' +
                    '<td><input type="text" class="setting_sku_procurementPrice" value="' + alreadySettingProcurementPrice + '"/></td>' +
                    '<td><input type="text" class="setting_sku_procurementEPrice" value="' + alreadySettingEPrice + '"/></td>' +
                    '<td><input type="text" class="setting_sku_procurementGlodPrice" value="' + alreadySettingGlodPrice + '"/></td>' +
                    '<td><img src="/img/add.png" alt="" id="'+"photo"+rowCount+'" width="60" height="60" >\n' +
                    '<input type="file" id="'+"file"+rowCount+'" value="上传图片" accept="image/*" onchange="uploadPhoto(event)">\n' +
                    '<input type="hidden"  class="setting_sku_photo"  value="' + alreadySetSkuPhoto + '">\n' +
                    ' </td></tr>';

            }
            SKUTableDom += "</table>";
        }
        $("#skuTable").html(SKUTableDom);
    });
});

function uploadPhoto(e){
    var file = e.target.files[0];
    var formData = new FormData();
    formData.append("file", file);
    $.ajax({
        url: '/system/goods/imageUpload',
        data: formData,
        type: 'post',
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function(data) {
            //成功
            var _input = e.target.parentNode.children[2];
            _input.value = data;
            var _img = e.target.parentNode.children[0];
            _img.src=data;
        }
    });
}
/**
 * 获取已经设置的SKU值
 */function getAlreadySetSkuVals() {
    alreadySetSkuVals = {};
    //获取设置的SKU属性值
    $("tr[class*='sku_table_tr']").each(function () {
        var skuPrice = $(this).find("input[type='text'][class*='setting_sku_price']").val();//SKU价格
        var skuStock = $(this).find("input[type='text'][class*='setting_sku_stock']").val();//SKU库存
        var goodsWeight = $(this).find("input[type='text'][class*='setting_sku_goodsWeight']").val();//SKU重量
        var skuId = $(this).find("input[type='text'][class*='setting_sku_id']").val();//商品编号
        var photo = $(this).find("input[type='text'][class*='setting_sku_photo']").val();//商品编号
        var photo = $(this).find("input[type='text'][class*='setting_sku_agentPrice']").val();//商品编号
        var procurementPrice = $(this).find("input[type='text'][class*='setting_sku_procurementPrice']").val();//商品采购价
        if (skuPrice || skuStock) {//已经设置了全部或部分值
            var propvalids = $(this).attr("propvalids");//SKU值主键集合
            var propids = $(this).attr("propids");//SKU值主键集合
            var propvalnames = $(this).attr("propvalnames");//SKU值主键集合
            var agentPrice = $(this).attr("propnames");//SKU值主键集合
            alreadySetSkuVals[propvalids] = {
                "skuPrice": skuPrice,
                "skuStock": skuStock,
                "goodsWeight": goodsWeight,
                "skuId": skuId
            }
        }
    });
}


function hhh(goodId) {
    var spec = [];
    $("#but").attr("disabled", true);
    alreadySetSkuVals = {};
    //获取设置的SKU属性值
    $("tr[class*='sku_table_tr']").each(function () {
        var skuPrice = $(this).find("input[type='text'][class*='setting_sku_price']").val();//SKU价格
        var skuStock = $(this).find("input[type='text'][class*='setting_sku_stock']").val();//SKU库存
        var goodsWeight = $(this).find("input[type='text'][class*='setting_sku_goodsWeight']").val();//SKU重量
        var agentPrice = $(this).find("input[type='text'][class*='setting_sku_agentPrice']").val();//SKU代理价
        var skuId = $(this).find("input[type='text'][class*='setting_sku_id']").val();//商品编号
        var photo = $(this).find("input[type='hidden'][class*='setting_sku_photo']").val();//商品照片
        var procurementPrice = $(this).find("input[type='text'][class*='setting_sku_procurementPrice']").val();//商品采购价
        var procurementEPrice = $(this).find("input[type='text'][class*='setting_sku_procurementEPrice']").val();//商品e卡价
        var procurementGlodPrice = $(this).find("input[type='text'][class*='setting_sku_procurementGlodPrice']").val();//商品金卡价
        if (skuPrice || skuStock) {//已经设置了全部或部分值
            var propvalids = $(this).attr("propvalids");//SKU值主键集合
            var propids = $(this).attr("propids");//SKU规格组主键集合
            var propvalnames = $(this).attr("propvalnames");//SKU规格值
            var propnames = $(this).attr("propnames");//SKU规格组名
            alreadySetSkuVals = {
                "name": propnames,//规格组名 集合
                "text": propvalnames,//规格值
                "propvalids": propvalids,//规格值 值code
                "propids": propids,//规格组 code
                "skuPrice": skuPrice,
                "skuStock": skuStock,
                "skuId": skuId,
                "agentPrice": agentPrice,
                "photo":photo,
                "goodsWeight": goodsWeight,
                "procurementPrice": procurementPrice,
                "procurementEPrice": procurementEPrice,
                "procurementGlodPrice": procurementGlodPrice
            };
            spec.push(alreadySetSkuVals);
        }
    });
    var specParams = getSpecValue();
    $.ajax({
        url: "/system/goods/addSku",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(
            {
                "goodId": goodId,
                "spec": spec,
                "specParams": specParams
            }),
        success: function (res) {
            if (res) {
                alert("添加成功");
            }

        }
    });
}


function getSpecValue() {
    var specs = [];
    var lis = document.querySelectorAll(".SKU_TYPE li");
    for (var i = 0; i < (lis.length - 1); i++) {
        var spec = {};
        var specId = lis[i].getAttribute("propid");//参数id
        var specName = lis[i].getAttribute("sku-type-name");//参数名字
        spec.specId = specId;
        spec.specName = specName;
        specs.push(spec);
    }
    var specValues = [];
    var inputs = document.getElementsByClassName("model_sku_val sku_value");
    for (var i = 0; i < inputs.length; i++) {
        var specValue = {};
        var specId = inputs[i].getAttribute("propid");//参数id
        var valueCode = inputs[i].getAttribute("propvalid");//属性id
        var valueName = inputs[i].getAttribute("value");//属性值
        specValue.specId = specId;
        specValue.valueCode = valueCode;
        specValue.valueName = valueName;
        specValues.push(specValue);
    }
    var values = [];
    for (var i = 0; i < specs.length; i++) {
        values = [];
        for (var j = 0; j < specValues.length; j++) {
            if (specs[i].specId === specValues[j].specId) {
                values.push(specValues[j]);
            }
        }
        specs[i].values = values;
    }
    return specs;
}