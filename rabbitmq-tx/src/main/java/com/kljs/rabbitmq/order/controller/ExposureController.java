package com.kljs.rabbitmq.order.controller;

import com.kljs.rabbitmq.order.dao.db1.AdvertisementMapperDB1;
import com.kljs.rabbitmq.order.dao.db1.BalanceFlowMapperDB1;
import com.kljs.rabbitmq.order.dao.db1.ExposureTouchRecordMapperDB1;
import com.kljs.rabbitmq.order.dao.db1.ShopBalanceMapperDB1;
import com.kljs.rabbitmq.order.dao.db2.AdvertisementMapperDB2;
import com.kljs.rabbitmq.order.dao.db2.BalanceFlowMapperDB2;
import com.kljs.rabbitmq.order.dao.db2.ExposureTouchRecordMapperDB2;
import com.kljs.rabbitmq.order.dao.db2.ShopBalanceMapperDB2;
import com.kljs.rabbitmq.order.dao.db3.AdvertisementMapperDB3;
import com.kljs.rabbitmq.order.dao.db3.BalanceFlowMapperDB3;
import com.kljs.rabbitmq.order.dao.db3.ExposureTouchRecordMapperDB3;
import com.kljs.rabbitmq.order.dao.db3.ShopBalanceMapperDB3;
import com.kljs.rabbitmq.order.model.Advertisement;
import com.kljs.rabbitmq.order.model.AdvertisementMessage;
import com.kljs.rabbitmq.order.model.BalanceFlow;
import com.kljs.rabbitmq.order.model.ExposureTouchRecord;
import com.kljs.rabbitmq.order.util.MapperUtil;
import com.kljs.rabbitmq.order.util.MyConnectionFactoryUtil;
import com.rabbitmq.client.*;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
/**
 * pcitc
 */
@RestController
@RequestMapping("/exposureController")
public class ExposureController {

    private static int DEFAULT_COST = 100;

    /**DB1**/
    @Autowired
    private AdvertisementMapperDB1 advertisementMapperDB1;
    @Autowired
    private ShopBalanceMapperDB1 shopBalanceMapperDB1;
    @Autowired
    private BalanceFlowMapperDB1 balanceFlowMapperDB1;
    @Autowired
    private ExposureTouchRecordMapperDB1 exposureTouchRecordMapperDB1;
    /**DB2**/
    @Autowired
    private AdvertisementMapperDB2 advertisementMapperDB2;
    @Autowired
    private ShopBalanceMapperDB2 shopBalanceMapperDB2;
    @Autowired
    private BalanceFlowMapperDB2 balanceFlowMapperDB2;
    @Autowired
    private ExposureTouchRecordMapperDB2 exposureTouchRecordMapperDB2;
    /**DB3**/
    @Autowired
    private AdvertisementMapperDB3 advertisementMapperDB3;
    @Autowired
    private ShopBalanceMapperDB3 shopBalanceMapperDB3;
    @Autowired
    private BalanceFlowMapperDB3 balanceFlowMapperDB3;
    @Autowired
    private ExposureTouchRecordMapperDB3 exposureTouchRecordMapperDB3;
    @Autowired
    private MapperUtil mapperUtil;

//    @RequestMapping(value = "/SearchAndCost/db1/no_transaction", method = RequestMethod.POST)
////    @Transactional(value = "db1TransactionManager",rollbackFor = Exception.class)
////    @Transactional
//    public String searchAndCostDB1NoTransaction() {
//        //1、找广告
//        Advertisement advertisement = advertisementMapperDB1.getRankOne();
//        //2、账户扣费
//        shopBalanceMapperDB1.costFromAd(advertisement.getShopId(), DEFAULT_COST);
//        if(1 < 2) {
//            throw new RuntimeException("单库无事务出错");
//        }
//        //3、账户流水
//        BalanceFlow balanceFlow = new BalanceFlow(advertisement.getShopId(), LocalDateTime.now(), DEFAULT_COST);
//        balanceFlowMapperDB1.insert(balanceFlow);
//        //4、曝光流水
//        ExposureTouchRecord exposureTouchRecord = new ExposureTouchRecord(advertisement.getShopId(), System.currentTimeMillis(), DEFAULT_COST, LocalDateTime.now(), advertisement.getId());
//        exposureTouchRecordMapperDB1.insert(exposureTouchRecord);
//        return "ok";
//    }

    @RequestMapping(value = "/SearchAndCost/db1/transaction", method = RequestMethod.POST)
    @Transactional
    public String searchAndCostDB1WithTransaction() {
        //1、找广告
        Advertisement advertisement = advertisementMapperDB1.getRankOne();
        //2、账户扣费
        shopBalanceMapperDB1.costFromAd(advertisement.getShopId(), DEFAULT_COST);
        //3、账户流水
        BalanceFlow balanceFlow = new BalanceFlow(advertisement.getShopId(), new Date(System.currentTimeMillis()), DEFAULT_COST);
        balanceFlowMapperDB1.insert(balanceFlow);
        //4、曝光流水
        ExposureTouchRecord exposureTouchRecord = new ExposureTouchRecord(advertisement.getShopId(),
                System.currentTimeMillis(), DEFAULT_COST, new Date(System.currentTimeMillis()), advertisement.getId());
        exposureTouchRecordMapperDB1.insert(exposureTouchRecord);
        return "ok";
    }

    @RequestMapping(value = "/SearchAndCost/db123/transaction", method = RequestMethod.POST)
//    @Transactional(value = "db1TransactionManager",rollbackFor = Exception.class)
    @Transactional
    public String searchAndCosDB123() {
        try {
            //1、找广告
            Thread.sleep(400);
            Advertisement advertisement = advertisementMapperDB1.getRankOne();
            //2、账户扣费
            Thread.sleep(400);
            shopBalanceMapperDB1.costFromAd(advertisement.getShopId(), DEFAULT_COST);
            //3、账户流水
            Thread.sleep(400);
            BalanceFlow balanceFlow = new BalanceFlow(advertisement.getShopId(), new Date(System.currentTimeMillis()), DEFAULT_COST);
            balanceFlowMapperDB2.insert(balanceFlow);
            //4、曝光流水
            Thread.sleep(400);
            ExposureTouchRecord exposureTouchRecord = new ExposureTouchRecord(advertisement.getShopId(), System.currentTimeMillis(), DEFAULT_COST, new Date(System.currentTimeMillis()), advertisement.getId());
            exposureTouchRecordMapperDB3.insert(exposureTouchRecord);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "/SearchAndCost/db123/mq_transaction", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public String mqTransaction() throws Exception {
        try {
            //1、找广告    商家-金额
            //xgboost、thread ——> 店铺筛选
            //redis  ——>  金额
            Advertisement advertisement = new Advertisement();
            advertisement.setName("这就是大灌篮");
            advertisement.setShopId(2000L);
            advertisement.setId(System.currentTimeMillis());
            advertisementMapperDB1.insert(advertisement);
            //2、mq事务
            AdvertisementMessage advertisementMessage = new AdvertisementMessage(advertisement.getId(),
                    advertisement.getShopId(), System.currentTimeMillis(), DEFAULT_COST, System.currentTimeMillis());
            this.mqSend(advertisementMessage);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 发送消息   给mq发包裹  mq 支持事务
     * @param advertisementMessage
     * @throws Exception
     */
    private void mqSend(AdvertisementMessage advertisementMessage) throws Exception {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory connectionFactory = MyConnectionFactoryUtil.getConnectionFactory();
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();

            String json = mapperUtil.writeValueAsString(advertisementMessage);

//            /**
//             * mq的事务机制
//             */
//            channel.addConfirmListener(new ConfirmListener() {
//                @Override
//                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
//                    System.out.println("==============handleAck==============");
//                }
//
//                @Override
//                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
//                    System.out.println("==============handleNack==============");
//                }
//            });
//            channel.addReturnListener(new ReturnListener() {
//                @Override
//                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    System.out.println("响应状态码-ReplyCode："+replyCode);
//                    System.out.println("响应内容-ReplyText："+replyText);
//                    System.out.println("Exchange:"+exchange);
//                    System.out.println("RouteKey"+routingKey);
//                    System.out.println("投递失败的消息："+ new String(body,"UTF-8") );
//                }
//            });
//
//            channel.confirmSelect();

            channel.exchangeDeclare("advertisement", ExchangeTypes.TOPIC, true);
            channel.queueDeclare("exposure", true, false, false, null);
            channel.queueBind("exposure", "advertisement", "exposure_event");

            //开启mq事务
            channel.txSelect();
            channel.basicPublish("advertisement", "exposure_event", true,  MessageProperties.PERSISTENT_TEXT_PLAIN, json.getBytes());
//            if(1 < 2) {
//                throw new RuntimeException("故意找茬");
//            }
            //null   pointer
            //mq 提交事务
            channel.txCommit();
        } catch (Exception e) {
            //回滚
            channel.txRollback();
            //业务回滚
            throw e;
        } finally {
            if(null != channel) {
                channel.close();
            }
            if(null != channel) {
                connection.close();
            }
        }
    }

    @RequestMapping(value = "/SearchAndCost/db123/mq_consumer_transaction", method = RequestMethod.POST)
    @Transactional
    public void mqConsumer() throws Exception {
        Connection connection = null;
        Channel channel = null;
        GetResponse getResponse = null;
        try {
            ConnectionFactory connectionFactory = MyConnectionFactoryUtil.getConnectionFactory();
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();

            getResponse = channel.basicGet("exposure", false);

            if(null == getResponse) {
                System.out.println("⚠️⚠️⚠️⚠️⚠️无消息处理");
                return;
            }

            AdvertisementMessage advertisementMessage = mapperUtil.readValue(new String (getResponse.getBody()), AdvertisementMessage.class);
            System.out.println("=======advertisementMessage=======" + advertisementMessage);

            /**DB业务**/
            //2、db1账户扣费
            shopBalanceMapperDB1.costFromAd(advertisementMessage.getShopId(), advertisementMessage.getCost());
            //3、db2账户流水
            BalanceFlow balanceFlow = new BalanceFlow(advertisementMessage.getShopId(), new Date(advertisementMessage.getTimestamp()), DEFAULT_COST);
            balanceFlowMapperDB2.insert(balanceFlow);
            //4、db3曝光流水
            ExposureTouchRecord exposureTouchRecord = new ExposureTouchRecord(advertisementMessage.getShopId(), System.currentTimeMillis(), DEFAULT_COST, new Date(advertisementMessage.getTimestamp()), advertisementMessage.getAdId());
            exposureTouchRecordMapperDB3.insert(exposureTouchRecord);

            channel.basicAck(getResponse.getEnvelope().getDeliveryTag(), false);
            System.out.println("mqConsumer 处理消息成功了");
        } catch (Exception e) {
            System.out.println("⚠️⚠️⚠️⚠️⚠️mqConsumer 处理消息失败了");
            if(null != getResponse) {
                channel.basicNack(getResponse.getEnvelope().getDeliveryTag(), false, true);
            }
            e.printStackTrace();
        } finally {
            if(null != channel) {
                channel.close();
            }
            if(null != connection) {
                connection.close();
            }
        }
    }

}
