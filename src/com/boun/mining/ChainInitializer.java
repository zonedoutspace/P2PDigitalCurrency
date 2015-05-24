package com.boun.mining;

import java.util.ArrayList;

import com.boun.operations.BlockOperations;
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
		output.setReceiverPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgMW2xEB3LTViNVRiNZNuG3GnYt3ZBFz5SmhQLSh6rJ1LZYaW1VPXVYDpaAywwUJtPKnbtqBfgndtyCSpJTrsKzHy78qzrs2");
		
		//block -1, transaction 10
		TransactionOutput output1 = new TransactionOutput();
		output1.setAmount(150);
		output1.setReceiverPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgQ1uwHQHtJNnJmXbLdiu1HtjYYvJVjaFyr4uyAUXWfrovu4V1r8ruzK195dybFiJr6N7Rk8PH7bqt8pEyWnwfX29tTSyHLc");
		
		
		ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
		outputs.add(output);
		outputs.add(output1);
		
		content.setInputs(inputs);
		content.setOutputs(outputs);
		content.setTransationDate(DateOperations.getCurrentDate());
		
		Gson gson = new Gson();
		
		
		transaction.setContent(content);
		transaction.setSenderPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgdXsuq51dYuFj8Agfd2DXtJBajv4X7b8dsDUcLs7oDFWQBEcfYjvxYegL6Lhm8wS4G6CLyBvRKEkM8PPpdV8B729SnfwhF6");
		transaction.setSign(Generator.sign(Generator.getPrivateKey("23g8Q4bQCNC9fo3NhW2KdUsBUqKZp1exZTmQob7mRM4uyva4cV1UTB3HgZTg71DiS6w2wcsmaoL1k7hScTVg6rjrhtbh3S723XxWQK7NMfFrjC2n8of34dPTfHrfYoV3y1MosA3Cy5F5UC1Ve1QuiFvAFdkkmgeUREnWBRMgCRFHFrFRrRDfuX3D1PDeRpVKEfofm56xcfQBAyXHzmwZt2SoACp7ojmXhVvLD1WC8cnRvY54Snko5HbnZcGaPS789h7zLQ2bhHg4kTwmksmNgpzhX2hHZJkkrVZEkhG8usZyXpGymDV2a19TPoAuMh4S8mVuUz2aCXj91ALZjneNbWcUx4LKhySKPjK9sfrCdisvqnCpoKzwFHXWF9YTNTwLzJhQAKfhVYm5wkK3THRaAY1mo9vWFCchihE2FcsNYHSfdVKHxRgNe6WiuFyZHaBRJyi6gSwGUpWC9y7uYJ7it"), gson.toJson(content)));
		
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
		output3.setReceiverPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgapsLyHgoM1Uq8EbwbNX1RGxZdXJXu7LDSzM17Z8yUp7GpiYbDPa1FuGZL97nd2vLSoh2ZbxfdLpWaiv3xSRvHLsgapYZiG");
		
		//block -1, transaction 10
		TransactionOutput output4 = new TransactionOutput();
		output4.setAmount(150);
		output4.setReceiverPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgQ1uwHQHtJNnJmXbLdiu1HtjYYvJVjaFyr4uyAUXWfrovu4V1r8ruzK195dybFiJr6N7Rk8PH7bqt8pEyWnwfX29tTSyHLc");
		
		
		ArrayList<TransactionOutput> outputs2 = new ArrayList<TransactionOutput>();
		outputs2.add(output3);
		outputs2.add(output4);
		
		content1.setInputs(inputs1);
		content1.setOutputs(outputs2);
		content1.setTransationDate(DateOperations.getCurrentDate());

		
		
		transaction1.setContent(content1);
		transaction1.setSenderPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KhAuAQRRuJb4mAoRY3KGgQdEUTWidWkUSSCMFkDrocR9nxevarqadZ5KDXKDEuXCZj3QGJveDAbdoHgDexMryVDijkLJrRFW");
		transaction1.setSign(Generator.sign(Generator.getPrivateKey("5coherapciktyxkAaprrWi5a182qUGRfubQ1eT5ZxhscbrKP8RbKXRZYeGXg9hox98NTHC4ExcZUnMPR3jsgiksLbdV7YD9mEx1Fm7vKS9Der7zzReuWpm6qcTQZ4zjQCHHEk59kFdEpWcc65DHEGoyiz8YPt2rLM58PzVB89GFtuf8j4DopC5NTDD5YFF77dfhS4QQzbsC8C5nGX262KZFba4PUxp1QNaWRRtxV4Euxfs7N3ZqKhknZKN9EeG5k7ym2qLsW34nsYPMtEtCKDJGpW3hiAa6iw2ozsULmbWLA2WHPywyeEeDcKgCHqTSwaFd3PwwQrHBATAnAXDt1Ydn6DM1mzaQEMrqKK9w9BwLgeydJkMAuFfV8AQzVWiuj8W9DByfxP1MTtSN5J3jVvxgVb2njWWsnoNndcL1utC15kPocY4ecVyVGGLpR1MbWP47WQVYf9bsTY5dPpjfUJn"), gson.toJson(content1)));
		
		
		/////////////////////////////////////////////////////////////////////////////
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		transactions.add(transaction1);
		
		
		Block block = BlockOperations.mine(1, transactions, "NO HASH FOR FIRST BLOCK",Properties.TARGET);
		System.out.println(gson.toJson(block));
		
		BlockOperations.writeBlock(block);
		Properties.setNumberOfBlock(1);
		Properties.setNumberOfDownloadedBlock(1);
		
		
		
	}
	
	
}
