# 传销自检 APP

已内置：
- 30 项传销特征自检题
- 否/不确定/是三档计分
- 0-60 分，5 个风险等级
- 上传图片已做成桌面图标

## 在 GitHub 自动生成 APK

1. 新建一个 GitHub 仓库。
2. 把本文件夹全部上传到仓库根目录，必须包含 `.github/workflows/build-apk.yml`。
3. 点仓库顶部 `Actions`。
4. 点左侧 `Build Android APK`。
5. 点 `Run workflow`。
6. 等待完成后，在页面底部 `Artifacts` 下载 `传销自检APP-debug`。
7. 解压后得到 `传销自检APP-debug.apk`，发到安卓手机安装即可。

如手机提示“未知来源”，在手机设置中允许浏览器/文件管理器安装未知应用。
