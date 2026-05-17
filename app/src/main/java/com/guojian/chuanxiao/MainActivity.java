package com.guojian.chuanxiao;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.text.method.ScrollingMovementMethod;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private final ArrayList<RadioGroup> groups = new ArrayList<>();
    private LinearLayout resultBox;
    private TextView resultText;

    private final String[] questions = new String[]{
            "1. 是否要求先交入门费、加盟费、培训费、资料费、保证金或购买高价产品才能加入？",
            "2. 是否承诺“低投入、高回报、稳赚不赔、短期翻倍”？",
            "3. 是否主要收入来自发展下线、拉人头、团队人数或层级返利？",
            "4. 是否让你不断邀请亲友、同学、同事加入，并强调“先从身边人开始”？",
            "5. 是否有上下线、团队、级别、经理、总监、董事等层级称呼？",
            "6. 是否要求每天开会、上课、听讲、喊口号、写心得或接受洗脑式培训？",
            "7. 是否反复贬低打工、上班、传统生意，鼓吹“这是唯一翻身机会”？",
            "8. 是否故意不让你清楚了解公司真实地址、营业执照、产品来源、财务数据？",
            "9. 是否产品价格明显虚高、质量普通，购买目的不是使用而是获得资格或返利？",
            "10. 是否没有正常门店、正常客户和真实消费，主要靠成员内部购买？",
            "11. 是否让你借钱、贷款、套信用卡、抵押资产或向亲友筹钱加入？",
            "12. 是否强调“保密”“不要告诉外人”“别人不懂会阻拦你”？",
            "13. 是否经常说“国家暗中支持”“合法但不能公开宣传”等话术？",
            "14. 是否把质疑者说成“负能量”“穷人思维”“没有格局”？",
            "15. 是否限制你离开、限制手机、限制与家人联系，或有人陪同看管？",
            "16. 是否以旅游、考察、面试、相亲、项目合作等名义把人邀到异地？",
            "17. 是否要求新人必须听完几天课程才允许判断，不让你马上离开？",
            "18. 是否组织频繁聚餐、住宿、集体生活，制造强烈团队依赖感？",
            "19. 是否用成功学、暴富故事、豪车豪宅图片刺激你投入？",
            "20. 是否总说“现在是早期机会，晚了就没有位置”？",
            "21. 是否没有清晰劳动合同、工资标准、社保、发票或正规收据？",
            "22. 是否收益规则复杂，靠级差、碰对、见点奖、领导奖、推荐奖等计算？",
            "23. 是否要求你购买多份产品、多个点位或重复投资来升级？",
            "24. 是否提现困难、退款困难，或者退款要扣大量费用？",
            "25. 是否以“区块链、数字货币、原始股、养老、健康、消费返利”等包装拉人？",
            "26. 是否经常要求晒转账、晒收益、晒团队人数，却无法提供真实审计证明？",
            "27. 是否让成员统一朋友圈话术，夸大宣传项目和收入？",
            "28. 是否声称认识领导、有内部政策、有特殊渠道，但拿不出公开可靠文件？",
            "29. 是否劝你辞职、退学、离家，专心投入这个项目？",
            "30. 是否你身边多数参与者并没有靠产品销售赚钱，而是靠继续发展新人维持？"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scroll = new ScrollView(this);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(26, 28, 26, 40);
        root.setBackgroundColor(Color.rgb(250,250,250));
        scroll.addView(root);

        TextView title = tv("传销活动自检表", 25, Color.rgb(190,0,0), true);
        title.setGravity(Gravity.CENTER);
        root.addView(title);

        TextView sub = tv("本工具仅用于风险提示。每题选择：否=0分，不确定=1分，是=2分。分数越高，传销风险越大。", 15, Color.DKGRAY, false);
        sub.setPadding(0,12,0,18);
        root.addView(sub);

        for (String q : questions) addQuestion(root, q);

        Button btn = new Button(this);
        btn.setText("立即计算风险等级");
        btn.setTextSize(18);
        btn.setTextColor(Color.WHITE);
        btn.setBackgroundColor(Color.rgb(215,25,32));
        root.addView(btn, new LinearLayout.LayoutParams(-1, dp(52)));

        resultBox = new LinearLayout(this);
        resultBox.setOrientation(LinearLayout.VERTICAL);
        resultBox.setPadding(18,18,18,18);
        resultBox.setVisibility(View.GONE);
        resultBox.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams rbp = new LinearLayout.LayoutParams(-1, -2);
        rbp.setMargins(0,18,0,0);
        root.addView(resultBox, rbp);

        resultText = tv("", 17, Color.BLACK, false);
        resultText.setMovementMethod(new ScrollingMovementMethod());
        resultBox.addView(resultText);

        btn.setOnClickListener(v -> calculate());
        setContentView(scroll);
    }

    private TextView tv(String s, int sp, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(sp);
        t.setTextColor(color);
        t.setLineSpacing(4,1.05f);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private void addQuestion(LinearLayout root, String q) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(18,16,18,14);
        card.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1, -2);
        cp.setMargins(0,0,0,12);
        root.addView(card, cp);

        TextView qt = tv(q, 16, Color.rgb(30,30,30), true);
        card.addView(qt);

        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.HORIZONTAL);
        String[] opts = {"否 0分", "不确定 1分", "是 2分"};
        for (int i=0;i<3;i++) {
            RadioButton rb = new RadioButton(this);
            rb.setText(opts[i]);
            rb.setTextSize(15);
            rb.setId(1000 + groups.size()*10 + i);
            rg.addView(rb);
        }
        rg.check(1000 + groups.size()*10);
        card.addView(rg);
        groups.add(rg);
    }

    private void calculate() {
        int total = 0;
        for (int idx=0; idx<groups.size(); idx++) {
            int checked = groups.get(idx).getCheckedRadioButtonId();
            int val = checked - (1000 + idx*10);
            if (val < 0 || val > 2) val = 0;
            total += val;
        }
        String level;
        String advice;
        if (total <= 12) {
            level = "一级：低风险";
            advice = "目前传销特征较少，但仍要核验营业执照、合同、发票、产品真实销售和退款规则。";
        } else if (total <= 24) {
            level = "二级：可疑风险";
            advice = "已出现多项异常。不要追加投入，不要拉亲友加入，先让家人或律师一起核查。";
        } else if (total <= 36) {
            level = "三级：高风险";
            advice = "高度疑似传销或变相传销。建议立即暂停转账、保留聊天记录、转账凭证、培训资料和人员名单。";
        } else if (total <= 48) {
            level = "四级：严重风险";
            advice = "风险很高。尽快离开相关组织，联系可信家人，不再参加培训会议，不再发展新人。";
        } else {
            level = "五级：极高风险";
            advice = "极可能已经深陷传销活动。请优先保证人身安全，停止付款和拉人，必要时报警求助。";
        }
        String safety = "\n\n建议保存证据：\n聊天记录、转账记录、收款账户、合同票据、课程资料、聚会地址、组织者姓名电话、拉人话术。\n\n紧急提醒：\n不要争吵硬劝，不要继续交钱，不要贷款，不要把亲友带入。若被限制人身自由或威胁，立即联系家人并报警。";
        resultText.setText("总分：" + total + " / 60\n风险等级：" + level + "\n\n" + advice + safety);
        resultBox.setVisibility(View.VISIBLE);
    }

    private int dp(int v) { return (int)(v * getResources().getDisplayMetrics().density + 0.5f); }
}
