package com.boun.mining;

import java.util.ArrayList;

import com.boun.operations.BlockOperations;
import com.boun.operations.TransactionOperations;
import com.boun.server.Properties;
import com.boun.signature.Generator;
import com.boun.structures.Block;
import com.boun.structures.Transaction;
import com.boun.structures.TransactionContent;
import com.boun.structures.TransactionInput;
import com.boun.structures.TransactionOutput;
import com.boun.util.DateOperations;
import com.google.gson.Gson;

public class ChainInitializer {

	
	public static void main(String[] args) throws Exception{
		
		Transaction transaction = new Transaction();
		
		TransactionContent content = new TransactionContent();
		
		TransactionInput input1 = new TransactionInput();
		input1.setBlockNumber(-1);
		input1.setTransactionOrder(3);
		input1.setOutputOrder(0);
		input1.setAmount(100);
		

		TransactionInput input2 = new TransactionInput();
		input2.setBlockNumber(-1);
		input2.setTransactionOrder(3);
		input2.setOutputOrder(1);
		input2.setAmount(100);		
		
		
		
		ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
		inputs.add(input1);
		inputs.add(input2);
		
		
		
		//block -1, transaction 5
		TransactionOutput output = new TransactionOutput();
		output.setAmount(50);
		output.setReceiverPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgP8mkfvjaHbuUEXEQYY33GsD6BXfQDPEEFzd5hv1ktKmUgFXEDmcSWDJrdkZYscc3nWwohMKncVf4MAzoGyH3QCahmmExRE");
		
		//block -1, transaction 10
		TransactionOutput output1 = new TransactionOutput();
		output1.setAmount(150);
		output1.setReceiverPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgotQeCDqLHxfBCFV37Sdxu3KcKCtgmAWAbvvA5ZCehvBpag8WtJhejSfKgzGHhc86WfKprgJ2PM39QcWpJCq9Avt6EEMUiU");
		
		
		ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
		outputs.add(output);
		outputs.add(output1);
		
		content.setInputs(inputs);
		content.setOutputs(outputs);
		content.setTransationDate(DateOperations.getCurrentDate());
		
		Gson gson = new Gson();
		
		
		transaction.setContent(content);
		transaction.setSenderPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgZQuQfo1yj5JzjBFCJsURMrFuCKiwr3S2Z6gwvGuAD2yTxoRHX3K9dB7mboVLppWWMqQB1kPVj9onAxJmiFYzjKi86keuDa");
		transaction.setSign(Generator.sign(Generator.getPrivateKey("23g8Q4bQCNC9fo3NhW2KdUsBUqKZp1exZTmQob7mRM4uyva4cUvt9zvD7CScwdRWA4FrUtgEgWdUrYAKbRR7dyZT577m3Rqd2FnVwH83hEDWacSLuqYDwCy5Vo51iSkMdMF2bmHwWoxuR9km92GvUwJNyZndisEP4XcvVXeEfyLSkVGzM37fHk6pftmDS3Scuq5rtxfZishNuqp5DjbfvjKhbimfdHFgBNNMoeWcAcLRv2AYZMmxdNWv4wdbn1RHSRco5i7tetAcGZLSxMb62WnnxVsaqyGc2r2GaGyRKzBeqYW5hpceZUNVFHWB3Uxsj1ddHtz2BfgRw5nsrkphmNiAAKCDEvJU55mt5EAEtQw1xqJUV6RxhTjQfMo3sbkjECbkz3GvM1ABW3JvGrrSRkovmYthgr2Tn4g4kK9maVvvfmPt3CNuDr6LMQ3AYu3GaocXp5NtkwVdQmR8sbxh3"), gson.toJson(content)));
		
		/////////////////////////////////////////////////////////////////////////////
		
		Transaction transaction1 = new Transaction();
		
		TransactionContent content1 = new TransactionContent();
		
		TransactionInput input3 = new TransactionInput();
		input3.setBlockNumber(-2);
		input3.setTransactionOrder(1);
		input3.setOutputOrder(0);
		input3.setAmount(100);
		

		TransactionInput input4 = new TransactionInput();
		input4.setBlockNumber(-2);
		input4.setTransactionOrder(1);
		input4.setOutputOrder(1);
		input4.setAmount(100);		
		
		
		
		ArrayList<TransactionInput> inputs1 = new ArrayList<TransactionInput>();
		inputs1.add(input3);
		inputs1.add(input4);
		
		
		
		//block -2, transaction 5
		TransactionOutput output3 = new TransactionOutput();
		output3.setAmount(50);
		output3.setReceiverPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgyEDBgKGdkR6D6WzqH4Pn4ELGBLRUTbpjysQcx92Y2PWdZbjrh4LPSypTb1BtipiEbAPRFiyWUDniQfdLMSCcAWfnwMJMKv");
		
		//block -1, transaction 10
		TransactionOutput output4 = new TransactionOutput();
		output4.setAmount(150);
		output4.setReceiverPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgotQeCDqLHxfBCFV37Sdxu3KcKCtgmAWAbvvA5ZCehvBpag8WtJhejSfKgzGHhc86WfKprgJ2PM39QcWpJCq9Avt6EEMUiU");
		
		
		ArrayList<TransactionOutput> outputs2 = new ArrayList<TransactionOutput>();
		outputs2.add(output3);
		outputs2.add(output4);
		
		content1.setInputs(inputs1);
		content1.setOutputs(outputs2);
		content1.setTransationDate(DateOperations.getCurrentDate());

		
		
		transaction1.setContent(content1);
		transaction1.setSenderPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgtQDnGTXxidzvH4pPtVzsVVfByUbqyvot2Ypcjt1wSuiA7sGWTLNh2GSqHTZSmAjYA5uWYZKQMbgUX5xsrVs19x8NE2bfiY");
		transaction1.setSign(Generator.sign(Generator.getPrivateKey("23g8Q4bQCNC9fo3NhW2KdUsBUqKZp1exZTmQob7mRM4uyva4cVJ3PMp4V87rRjFSNtZCMdETaiRAn7PMam8iKoQnf24KghCc7Zd2cqduV58EpHoVmekWksDGAESgp7eBsPJMxejTZoDZeVidnwkCdadGp5ShjseXwkDpk5vHBEi2ByokrxcMLcZasnAdQRf2nAdvPH1GJjFAsoToStDAPq1hhpgyJAgVryxv3eviZYeMLby3fUHh8CvW7mkEjmkCyP45Pj4KyrS9TvGMgoBbpzpDPKyXGk6UHmUqXJwde3NCz2At1WYaGFdWWxf4cjWtWid3K2fkJMtsDuSVr4PQQa6qScr86duCinXp6tnZ3azrLwpun9eLQWCVXGKSZ3MPvC3xmXkQR2piTHMf5XDowJUpzWkP6xSDnar1dmnMj4fWRrFUSJnShgKRvgW4D5AbeLZW5oB4HxyR4ehU1VLb8"), gson.toJson(content1)));
		
		
		/////////////////////////////////////////////////////////////////////////////
		
		
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		transactions.add(transaction1);
		
		System.out.println("1:"+TransactionOperations.checkTransaction(transaction));
		System.out.println("2:"+TransactionOperations.checkTransaction(transaction1));
		
		
		
		Block block = BlockOperations.mine(1, transactions, "NO HASH FOR FIRST BLOCK",Properties.TARGET);
		System.out.println(gson.toJson(block));
		
		BlockOperations.writeBlock(block);
		Properties.setNumberOfBlock(1);
		Properties.setNumberOfDownloadedBlock(1);
		
		
		
	}
	
	
	
	
	
}
