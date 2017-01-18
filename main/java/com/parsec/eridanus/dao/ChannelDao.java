package com.parsec.eridanus.dao;

import com.parsec.eridanus.po.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kevin on 2016/11/24.
 */
@Repository
public interface ChannelDao {
    void saveChannel(Channel channel);
    List<Channel> findAllChannel();
}
