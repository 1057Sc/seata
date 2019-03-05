/*
 *  Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.alibaba.fescar.common.util;


import static org.assertj.core.api.Assertions.assertThat;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import org.junit.Test;

/**
 * @author Otis.z
 * @date 2019/2/26
 */
public class NetUtilTest {

    private InetSocketAddress ipv4 = new InetSocketAddress(Inet4Address.getLocalHost().getHostName(), 3902);
    private InetSocketAddress ipv6 = new InetSocketAddress(Inet6Address.getLocalHost().getHostName(), 3904);

    public NetUtilTest() throws UnknownHostException {
    }

    @Test
    public void testToStringAddress() {
        try {
            String stringAddress = NetUtil.toStringAddress(InetSocketAddress.createUnresolved("127.0.0.1", 9828));
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NullPointerException.class);
        }
    }

    @Test
    public void testToStringAddress1() {
        assertThat(NetUtil.toStringAddress((SocketAddress) ipv4))
            .isEqualTo(ipv4.getAddress().getHostAddress() + ":" + ipv4.getPort());
        assertThat(NetUtil.toStringAddress((SocketAddress) ipv6)).isEqualTo(
            ipv6.getAddress().getHostAddress() + ":" + ipv6.getPort());
    }

    @Test
    public void testToStringAddress2() {
        assertThat(NetUtil.toStringAddress(ipv4)).isEqualTo(
            ipv4.getAddress().getHostAddress() + ":" + ipv4.getPort());
        assertThat(NetUtil.toStringAddress(ipv6)).isEqualTo(
            ipv6.getAddress().getHostAddress() + ":" + ipv6.getPort());
    }

    @Test
    public void testToIpAddress() throws UnknownHostException {
        assertThat(NetUtil.toIpAddress(ipv4)).isEqualTo(ipv4.getAddress().getHostAddress());
        assertThat(NetUtil.toIpAddress(ipv6)).isEqualTo(ipv6.getAddress().getHostAddress());
    }


    @Test
    public void testToInetSocketAddress() {
        try {
            NetUtil.toInetSocketAddress("23939:ks");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NumberFormatException.class);
        }
    }

    @Test
    public void testToInetSocketAddress1() {
        assertThat(NetUtil.toInetSocketAddress("kadfskl").getHostName()).isEqualTo("kadfskl");
    }


    @Test
    public void testToLong() {
        try {
            NetUtil.toLong("kdskdsfk");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NullPointerException.class);
        }
    }

    @Test
    public void testToLong1() {
        String[] split = "127.0.0.1".split("\\.");
        long r = 0;
        r = r | (Long.parseLong(split[0]) << 40);
        r = r | (Long.parseLong(split[1]) << 32);
        r = r | (Long.parseLong(split[2]) << 24);
        r = r | (Long.parseLong(split[3]) << 16);
        r = r | 0;
        assertThat(NetUtil.toLong("127.0.0.1")).isEqualTo(r);

    }


    @Test
    public void testGetLocalIp() {
        assertThat(NetUtil.getLocalIp()).isNotNull();
    }

    @Test
    public void testGetLocalHost() {
        assertThat(NetUtil.getLocalHost()).isNotNull();
    }

    @Test
    public void testGetLocalAddress() {
        assertThat(NetUtil.getLocalAddress()).isNotNull();
    }

}