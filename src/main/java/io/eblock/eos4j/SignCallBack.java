package io.eblock.eos4j;

/**
 * @author wangke
 * @description: TODO
 * @date 2020/6/10 5:43 下午
 */
public interface SignCallBack {
    byte[] sign(byte[] msgHash);
}
