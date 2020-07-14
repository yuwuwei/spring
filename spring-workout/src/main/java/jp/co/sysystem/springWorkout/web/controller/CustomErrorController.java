package jp.co.sysystem.springWorkout.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController extends AbstractErrorController {

  // URL定義
  private static final String ERROR_PATH=  "/error";

  ///ページ定義
  public static final String ERROR_PAGE = "page/error";

  @Autowired
  public CustomErrorController(ErrorAttributes errorAttributes) {
      super(errorAttributes);
  }

  @RequestMapping(ERROR_PATH)
  public String handleErrors(HttpServletRequest request, Model model) {
//      HttpStatus status = getStatus(request);
//
//      if (status.equals(HttpStatus.NOT_FOUND)) {
//          return "notFound";
//      }
//      if (status.equals(HttpStatus.FORBIDDEN)) {
//          return "forbidden";
//      }

      // エラー情報を取得
      Map<String, Object> attr = getErrorAttributes(request, true);
      model.addAttribute("status", getStatus(request).value());
      model.addAttribute("timestamp", attr.get("timestamp"));
      model.addAttribute("error", attr.get("error"));
      model.addAttribute("message", attr.get("message"));
      model.addAttribute("trace", attr.get("trace"));
      model.addAttribute("path", attr.get("path"));
      model.addAttribute("exception", attr);
      return ERROR_PAGE;
  }

  @Override
  public String getErrorPath() {
      return ERROR_PATH;
  }
}
