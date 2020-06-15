package com.kljs.rabbitmq.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTests {

	private static RestTemplate restTemplate = new RestTemplateBuilder()
			.setConnectTimeout(100000)        //连接超时为10秒
			.setReadTimeout(100000)           //请求超时为10秒
			.build();

//	private String db12_select = "http://localhost:9080/exposureController/db12/select";
//
//	/**
//	 * db1无事务
//	 */
//	private String search_cost_db1_no_transaction_url = "http://localhost:9080/exposureController/SearchAndCost/db1/no_transaction";
//	/**
//	 * db1有事务
//	 */
//	private String search_cost_db1_transaction_url = "http://localhost:9080/exposureController/SearchAndCost/db1/transaction";
//	/**
//	 * db123有事务
//	 */
//	private String search_cost_db12_transaction_url = "http://localhost:9080/exposureController/SearchAndCost/db123/transaction";

	/**
	 * mq 开始 事务
	 */
	private String search_cost_mq_transaction_url = "http://localhost:9080/exposureController/SearchAndCost/db123/mq_transaction";

	/**
	 * mq 处理 事务
	 */
	private String search_cost_mq_consumer_transaction_url = "http://localhost:9080/exposureController/SearchAndCost/db123/mq_consumer_transaction";

//	@Test
//	public void searchAd() {
//		long start = System.currentTimeMillis();
//		String response = restTemplate.postForObject(search_cost_db12_transaction_url, null, String.class);
//		long end = System.currentTimeMillis();
//		System.out.println("===========response===========" + response + "; cost = " + (end - start));
//	}

	@Test
	public void mqBegin() {
		long start = System.currentTimeMillis();
		String response = restTemplate.getForObject(search_cost_mq_transaction_url, null, String.class);
		//String response = restTemplate.postForObject(search_cost_mq_transaction_url, null, String.class);
		long end = System.currentTimeMillis();
		System.out.println("===========response===========" + response + "; cost = " + (end - start));
	}

	@Test
	public void mqEnd() {
		long start = System.currentTimeMillis();
		String response = restTemplate.postForObject(search_cost_mq_consumer_transaction_url, null, String.class);
		long end = System.currentTimeMillis();
		System.out.println("===========response===========" + response + "; cost = " + (end - start));
	}

//	@Test
//	public void test() throws Exception {
//		ConnectionFactory connectionFactory = MyConnectionFactoryUtil.getAliyunConnectionFactory();
//		Connection connection = connectionFactory.newConnection();
//		Channel channel = connection.createChannel();
//		channel.exchangeDeclare("kebi_exchange", ExchangeTypes.TOPIC, true);
//		channel.queueDeclare("kebi_queue", true, false, false, null);
//		channel.queueBind("kebi_queue", "kebi_exchange", "kebi");
//		String json = "hello world";
//		channel.basicPublish("kebi_exchange", "kebi", true,  MessageProperties.PERSISTENT_TEXT_PLAIN, json.getBytes());
//		System.out.println("发送成功");
//	}

//	@Test
//	public void mqConsumer() throws Exception {
//		Connection connection = null;
//		Channel channel = null;
//		try {
//			ConnectionFactory connectionFactory = MyConnectionFactoryUtil.getConnectionFactory();
//			connection = connectionFactory.newConnection();
//			channel = connection.createChannel();
//
//			GetResponse getResponse = channel.basicGet("exposure", true);
//			AdvertisementMessage advertisementMessage = mapperUtil.readValue(new String (getResponse.getBody()), AdvertisementMessage.class);
//			System.out.println("=======advertisementMessage=======" + advertisementMessage);
//			//2、账户扣费
//
//
//			//3、账户流水
//			//4、曝光流水
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(null != channel) {
//				channel.close();
//			}
//			if(null != connection) {
//				connection.close();
//			}
//		}
//
//	}


//	@Test
//	public void contextLoads() {
//
//		long startTime = System.currentTimeMillis();
//		System.out.println("start =" + LocalDateTime.now());
//		for(int i=0; i<amount; i++) {
//			final int index = i;
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						countDownLatch.await();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					new RequestOperate(index, search_cost_db1_url);
//				}
//			}).start();
//			countDownLatch.countDown();
//		}
//
//		long endTime = System.currentTimeMillis();
//		System.out.println("end =" + LocalDateTime.now());
//		System.out.println("cost =" + (endTime - startTime));
//
//		try {
//			Thread.sleep(100000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public class RequestOperate {
//
//		public RequestOperate (int index, String url) {
//			TradeOrderVo tradeOrderVo = new TradeOrderVo();
//			tradeOrderVo.setOrderId(new Long(index));
//			tradeOrderVo.setOrderStatus(0);
//			tradeOrderVo.setShopId(1000L);
//			tradeOrderVo.setUserId(new Long(index));
//			String info = "send request to server "+LocalDateTime.now()+", costTime = %d";
//			long start = System.currentTimeMillis();
//			try {
//				String response = restTemplate.postForObject(url, tradeOrderVo, String.class);
//			} catch (Exception e) {
//				System.out.println(String.format("index=%d, errorCount=%d", index, errorCount.incrementAndGet()));
//			} finally {
//				long end = System.currentTimeMillis();
//				System.out.println(String.format(info, (end - start)));
//			}
//		}
//	}

}
