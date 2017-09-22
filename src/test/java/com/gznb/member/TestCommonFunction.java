package com.gznb.member;

import com.github.pagehelper.PageInfo;
import com.gznb.member.entity.account.AccEWorkstation;
import com.gznb.member.entity.activity.AEActivityDetail;
import com.gznb.member.entity.member.MEMember;
import com.gznb.member.service.ActivityService;
import com.gznb.member.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringBootTest(classes = Application.class)
public class TestCommonFunction {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ActivityService activityService;

    @Test
    public void test() {
        String prefix = "1706";
        Random rd = new Random(System.currentTimeMillis());
        System.out.println(prefix + System.currentTimeMillis() + rd.nextLong());
    }

    @Test
    public void testMemberService() {
        MEMember member = new MEMember();
        member.setMemberName("安安");
        PageInfo pageInfo = memberService.getAll(member);

        System.out.println("******* result size: " + pageInfo.getList().size());
    }

    @Test
    public void testActivityService() {
        AEActivityDetail activityDetail = new AEActivityDetail();
        activityDetail.setActivityName("");
        PageInfo pageInfo = activityService.getAll(activityDetail);
        System.out.println("***** result size: " +  pageInfo.getList().size());

    }

    @Test
    public void testAccount() {
        AccEWorkstation workstation = AccEWorkstation.builder().workStationCode("").build();
    }
}
