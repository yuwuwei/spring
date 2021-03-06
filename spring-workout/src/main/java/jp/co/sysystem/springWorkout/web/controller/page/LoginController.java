package jp.co.sysystem.springWorkout.web.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sysystem.springWorkout.domain.table.User;
import jp.co.sysystem.springWorkout.service.LoginService;
import jp.co.sysystem.springWorkout.util.MessageUtil;
import jp.co.sysystem.springWorkout.web.form.LoginForm;
import lombok.extern.slf4j.Slf4j;

/**
 * ログイン画面コントローラー
 * @version 1.0.0 2020/05/13 新規作成
 */
@Controller
@EnableAutoConfiguration
@Slf4j
public class LoginController {

  @Autowired
  HttpSession session;

  @Autowired
  public MessageUtil msgutil;

  @Autowired
  public LoginService login;


  /// URL定義
  public static final String LOGIN_FORM_URL = "/";
  public static final String LOGIN_PROCESS_URL = "/login";
  public static final String LOGOUT_URL = "/logout";
  public static final String SEARCH_FORM_URL = "/searchPage";

  ///ページ定義
  public static final String LOGIN_PAGE = "page/login";
  public static final String SEARCH_PAGE = "page/search";

  ///ログイン情報セッション属性
  public static final String AUTHENTICATED = "authenticated";

  /**
   * ログイン画面表示
   * ログイン前でも表示可能
   */
  @RequestMapping(value = LOGIN_FORM_URL, method = RequestMethod.GET)
  public String showLoginPage(HttpServletRequest request, Model model) {
    // ログインフォームを格納
    model.addAttribute("loginForm", new LoginForm());

    //セッションにログイン情報があれば検索ページへリダイレクト
    if(session.getAttribute(AUTHENTICATED) != null) {
      return "redirect:" + SEARCH_FORM_URL;
    }

    return LOGIN_PAGE;
  }

  /**
   * ログイン処理<br>
   * ログイン成功である場合、検索ページへリダイレクトする。<br>
   * ログイン成功時にセッションに属性名：AUTHENTICATED 値：ログインユーザーのレコードを設定<br>
   * 各ページへの遷移時にAUTHENTICATEDの有無をチェックしてください。<br>
   * リダイレクトする理由は検索ページ遷移後ブラウザのリロードボタン押下でログイン時のPOSTが<br>
   * 再送されるのを防ぐため。（PRGパターン）
   * @param form
   * @param bindingResult
   * @param model
   * @return
   */
  @RequestMapping(value = LOGIN_PROCESS_URL, method = RequestMethod.POST)
  public String processLogin(
      @Validated @ModelAttribute LoginForm form,
      BindingResult bindingResult,
      Model model) {

    // BeanValidationの結果確認
    if (bindingResult.hasErrors()) {
      //ログインフォームからの入力値確認結果にエラーがある場合の処理

      // エラーメッセージをリソースファイルから取得
      String msg = msgutil.getMessage("login.failure");
      log.debug(msg);

      // ログインに失敗したら、もう一度ログイン画面
      // ログインフォームを格納
      model.addAttribute("loginForm", form);
      return LOGIN_PAGE;
    }

    //ログインID、パスワードでDB検索
    //存在しなかった場合BindingResultにエラーを設定する
    User u = login.checkLoginUser(form.getId(), form.getPassword(), bindingResult);

    // ログインユーザー情報の正当性判定
    if (null == u) {

      // エラーメッセージをリソースファイルから取得
      String msg = msgutil.getMessage("login.failure");
      log.debug(msg);

      // ログインに失敗したら、もう一度ログイン画面
      // ログインフォームを格納
      model.addAttribute("loginForm", form);
      return LOGIN_PAGE;
    }

    //ログイン状態を表す属性をセッションに追加
    session.setAttribute(AUTHENTICATED, u);
    //検索ページへリダイレクト
    return "redirect:" + SEARCH_FORM_URL;
  }

  /**
   * ログアウト
   * @param model
   * @return
   */
  @RequestMapping(value = LOGOUT_URL, method = RequestMethod.GET)
  public String logout(Model model) {
    // 既存セッションを削除
    session.invalidate();
    // 遷移先のログイン画面で使用する空のForm
    model.addAttribute("loginForm", new LoginForm());
    return LOGIN_PAGE;
  }
}
