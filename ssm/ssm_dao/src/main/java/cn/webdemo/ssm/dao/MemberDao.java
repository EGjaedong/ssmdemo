package cn.webdemo.ssm.dao;

import cn.webdemo.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by 63042 on 2019/6/25.
 *
 */
//@Repository("memberDao")
public interface MemberDao {

    @Select("select * from member where id = #{memberId}")
    public Member findById(int memberId) throws Exception;
}
