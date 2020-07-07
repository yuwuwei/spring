package jp.co.sysystem.springWorkout.web.controller.page;

//LoginControllerで定義した定数をインポート
import static jp.co.sysystem.springWorkout.web.controller.page.LoginController.*;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sysystem.springWorkout.domain.table.ResultTable;
import jp.co.sysystem.springWorkout.service.SearchService;
import jp.co.sysystem.springWorkout.util.MessageUtil;
import jp.co.sysystem.springWorkout.web.form.LoginForm;
import jp.co.sysystem.springWorkout.web.form.SearchForm;
import lombok.extern.slf4j.Slf4j;

/**
 * 検索画面コントローラー
 * @version 1.0.0 2020/05/13 新規作成
 */
@Controller
@EnableAutoConfiguration
@Slf4j
public class SearchController {

  @Autowired
  HttpSession session;

  @Autowired
  public MessageUtil msgutil;

  @Autowired
  public SearchService search;

  /// URL定義
  public static final String SEARCH_URL = "/search";
  public static final String SEARCH_FORM_URL = "/searchPage";
  public static final String UPDATE_USER_URL = "/updateUserPage";
  public static final String DELETE_USER_URL = "/deleteUserPage";
  public static final String ADD_USER_URL = "/addUserPage";

  ///ページ定義
  public static final String SEARCH_PAGE = "page/search";
  public static final String UPDATE_USER_PAGE = "page/update_user";
  public static final String DELETE_USER_PAGE = "page/delete_user";
  public static final String ADD_USER_PAGE = "page/add_user";

  /**
   * 検索処理
   * 入力されたID、名前、カナで前方一致検索し、結果リストを持って検索ページへ遷移
   * @param form
   * @param bindingResult
   * @param model
   * @return
   */
  @RequestMapping(value = SEARCH_URL, method = RequestMethod.POST)
  public String search(
      @Validated @ModelAttribute SearchForm form,
      BindingResult bindingResult,
      Model model) {

    //セッションにログイン情報が無ければログインページへリダイレクト
    if(session.getAttribute(AUTHENTICATED) == null) {
      model.addAttribute("loginForm", new LoginForm());
      return LOGIN_PAGE;
    }

    //セッションに検索フォームの内容を追加
    session.setAttribute("searchForm", form);

    //入力チェックでエラーがあった場合の処理
    if(bindingResult.hasErrors()) {
      //フォームの入力内容を保持して検索せずに検索ページに戻る
      model.addAttribute("searchForm", form);
      return SEARCH_PAGE;
    }

    //検索して結果を取得
    List<ResultTable> u = search.searchRecords(form.getId(), form.getName(), form.getKana());

    //結果が0件だったら検索画面に戻ってエラー表示
    if(u.size() == 0) {
      String msg = msgutil.getMessage("dbacces.searchFailed");
      model.addAttribute("msg", msg);
      model.addAttribute("searchForm", form);
      return SEARCH_PAGE;
    }


    model.addAttribute("result", u);
    model.addAttribute("searchForm", form);

    //更新、削除ボタンが対象レコードの情報を保持するために使用
    model.addAttribute("resultTable", new ResultTable());
    return SEARCH_PAGE;
  }

  /**
   * 検索ページ表示処理
   * セッションからフォームの入力内容を取得し、再検索する
   * 新規登録、更新、削除画面から戻ってきた場合それぞれに対応した文字列がURLのパラメータで渡される
   * @param status add,update,delete,nullのいずれかが入る
   * @param model
   * @return
   */
  @RequestMapping(value = SEARCH_FORM_URL, method = {RequestMethod.GET, RequestMethod.POST})
  public String searchPage(@ModelAttribute("status") String status, Model model) {

    //セッションにログイン情報が無ければログインページへリダイレクト
    if(session.getAttribute(AUTHENTICATED) == null) {
      model.addAttribute("loginForm", new LoginForm());
      return LOGIN_PAGE;
    }

    //各処理に対応した成功時メッセージをセット
    if(status != null) {
      if(status.equals("add")) {
        model.addAttribute("resultMsg", msgutil.getMessage("data.regist"));
      }
      else if(status.equals("update")) {
        model.addAttribute("resultMsg", msgutil.getMessage("data.update"));
      }
      else if(status.equals("delete")) {
        model.addAttribute("resultMsg", msgutil.getMessage("data.delete"));
      }
    }

    //セッションからフォームの入力内容を取得
    SearchForm form = (SearchForm) session.getAttribute("searchForm");

    //ログイン画面からの遷移時などの初回表示用処理
    if(form == null) {
      model.addAttribute("searchForm", new SearchForm());
      return SEARCH_PAGE;
    }

    //検索して結果を取得
    List<ResultTable> u = search.searchRecords(form.getId(), form.getName(), form.getKana());

    //結果が0件だったら検索画面に戻ってエラー表示
    if(u.size() == 0) {
      String msg = msgutil.getMessage("dbacces.searchFailed");
      model.addAttribute("msg", msg);
      model.addAttribute("searchForm", form);
      return SEARCH_PAGE;
    }


    model.addAttribute("result", u);
    model.addAttribute("searchForm", form);

    //更新、削除ボタンが対象レコードの情報を保持するために使用
    model.addAttribute("resultTable", new ResultTable());
    return SEARCH_PAGE;
  }


  /**
   * 【新規登録画面のコントローラーに移動予定】
   * 新規登録ボタンが押された時の処理
   * @param model
   * @return
   */
  @RequestMapping(value = ADD_USER_URL, method = RequestMethod.GET)
  public String addUser(Model model) {

    //セッションにログイン情報が無ければログインページへリダイレクト
    if(session.getAttribute(AUTHENTICATED) == null) {
      model.addAttribute("loginForm", new LoginForm());
      return LOGIN_PAGE;
    }

    return ADD_USER_PAGE;
  }

  /**
   * 【更新画面のコントローラーに移動予定】
   * 更新ボタンが押された時の処理
   * ResultTableクラスのidフィールドに更新対象のidを保持して変更入力画面へ遷移
   * @param rt
   * @param model
   * @return
   */
  @RequestMapping(value = UPDATE_USER_URL, method = RequestMethod.POST)
  public String updateUser(
      @ModelAttribute ResultTable rt, Model model) {

    //セッションにログイン情報が無ければログインページへリダイレクト
    if(session.getAttribute(AUTHENTICATED) == null) {
      model.addAttribute("loginForm", new LoginForm());
      return LOGIN_PAGE;
    }

    //idフィールド以外はnullが入っているので注意
    model.addAttribute("resultTable", rt);
    return UPDATE_USER_PAGE;
  }

  /**
   * 【削除画面のコントローラーに移動予定】
   * 削除ボタンが押された時の処理
   * ResultTableクラスのidフィールドに削除対象のidを保持して削除画面へ遷移
   * @param rt
   * @param model
   * @return
   */
  @RequestMapping(value = DELETE_USER_URL, method = RequestMethod.POST)
  public String deleteUser(
      @ModelAttribute ResultTable rt, Model model) {

    //セッションにログイン情報が無ければログインページへリダイレクト
    if(session.getAttribute(AUTHENTICATED) == null) {
      model.addAttribute("loginForm", new LoginForm());
      return LOGIN_PAGE;
    }

    //idフィールド以外はnullが入っているので注意
    model.addAttribute("resultTable", rt);
    return DELETE_USER_PAGE;
  }

  /**
   * テスト用メソッド
   * @param model
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(RedirectAttributes redirectAttributes, Model model) {

    //セッションにログイン情報が無ければログインページへリダイレクト
    if(session.getAttribute(AUTHENTICATED) == null) {
      model.addAttribute("loginForm", new LoginForm());
      return LOGIN_PAGE;
    }

    //処理成功メッセージとしてadd,update,deleteいずれかをセット
    redirectAttributes.addFlashAttribute("status", "add");
    return "redirect:" + SEARCH_FORM_URL;
  }

  /**
   * テスト用メソッド
   * @param model
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.GET)
  public String update(RedirectAttributes redirectAttributes, Model model) {

    //セッションにログイン情報が無ければログインページへリダイレクト
    if(session.getAttribute(AUTHENTICATED) == null) {
      model.addAttribute("loginForm", new LoginForm());
      return LOGIN_PAGE;
    }

    //処理成功メッセージとしてadd,update,deleteいずれかをセット
    redirectAttributes.addFlashAttribute("status", "update");
    return "redirect:" + SEARCH_FORM_URL;
  }

  /**
   * テスト用メソッド
   * @param model
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.GET)
  public String delete(RedirectAttributes redirectAttributes, Model model) {

    //セッションにログイン情報が無ければログインページへリダイレクト
    if(session.getAttribute(AUTHENTICATED) == null) {
      model.addAttribute("loginForm", new LoginForm());
      return LOGIN_PAGE;
    }

    //処理成功メッセージとしてadd,update,deleteいずれかをセット
    redirectAttributes.addFlashAttribute("status", "delete");
    return "redirect:" + SEARCH_FORM_URL;
  }
}
