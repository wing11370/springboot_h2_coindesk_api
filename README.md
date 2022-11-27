# springboot_h2_coindesk_api
JAVA開發的 比特幣匯率查詢系統

#使用方法

以下API的“currency”參數可以帶入"usd"(美金)、"gbp"(英鎊)、"eur"(歐元)

1.新增指定貨幣匯率

route：/{currency}

Method:POST

2.新增美金、英鎊、歐元匯率

route：{url}/message/{userid}

Method:POST

3.查詢指定貨幣匯率列表

route：/{currency}

Method:GET

4.查詢指定貨幣匯率id

route：/{currency}/{id}

Method:GET

5.修改指定貨幣匯率資料

route：/{currency}/{id}

Method:PATCH

Body
{
"date":"",
"rate":16.12
}

6.刪除指定貨幣匯率資料

route：/{currency}/{id}

Method:DELETE
