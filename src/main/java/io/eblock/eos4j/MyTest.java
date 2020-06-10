package io.eblock.eos4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.eblock.eos4j.api.vo.account.Account;
import io.eblock.eos4j.api.vo.transaction.Transaction;
import io.eblock.eos4j.ecc.EccTool;
import io.eblock.eos4j.utils.Base58;
import io.eblock.eos4j.utils.ByteUtils;
import io.eblock.eos4j.utils.Hex;
import io.eblock.eos4j.utils.Sha;

import java.math.BigInteger;

/**
 * @author wangke
 * @description: TODO
 * @date 2020/6/10 2:13 下午
 */
public class MyTest {
    static Rpc rpc = new Rpc("https://api.testnet.eos.io/");


    public static void main(String[] args) throws Exception {


        String pk = "x";

//        createAccount(pk);
        transfer(pk);
    }

    public static void createAccount(String pk) throws Exception{
        Transaction t2 = rpc.createAccount(pk,"ghpeihmofbws","renrenbitsgx", "EOS6p5SVF6qjU6A15kMNeWqXSU2e6LWg492D4mEJdUwkZRDZVk77c","EOS6p5SVF6qjU6A15kMNeWqXSU2e6LWg492D4mEJdUwkZRDZVk77c", 8192L, "0.1 SYS","0.1 SYS", 0L);
        System.out.println("创建成功 = " + t2.getTransactionId()+" \n ");
    }

    public static void transfer(String pk) throws Exception {
        final String s1 = EccTool.privateToPublic(pk);
        System.out.println("public: " + s1);

        final Account account = rpc.getAccount("ghpeihmofbws");
        ObjectMapper mapper = new ObjectMapper();
        final String s = mapper.writeValueAsString(account);
        System.out.println(s);

        try {
            Transaction t1 = rpc.transfer(pk, "eosio.token", "ghpeihmofbws", "kipuyzrdxoqq", "0.1000 TNT", "test");
            final String transactionId = t1.getTransactionId();
            System.out.println(transactionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPrivateKey(byte[] b) {
        byte[] a = {(byte) 0x80};
        byte[] private_key = ByteUtils.concat(a, b);
        byte[] checksum = Sha.SHA256(private_key);
        checksum = Sha.SHA256(checksum);
        byte[] check = ByteUtils.copy(checksum, 0, 4);
        byte[] pk = ByteUtils.concat(private_key, check);
        return Base58.encode(pk);
    }

}
