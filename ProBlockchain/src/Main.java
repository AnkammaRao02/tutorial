import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class Main {   // the NoobChain class 
	
	public static ArrayList<Block> blockchain = new ArrayList<>();
	public static int difficulty=5;

	public static void main(String[] args) {
		
		blockchain.add(new Block("Hi im the first block", "0"));
		System.out.println("Mining first block..");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash)); 
		System.out.println("mining the second block....");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
		System.out.println("mining the third block....");
		blockchain.get(2).mineBlock(difficulty);
		
		System.out.println("\n Block chain is valid..."+isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\n The Block chain");
		System.out.println(blockchainJson);
		
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
}
