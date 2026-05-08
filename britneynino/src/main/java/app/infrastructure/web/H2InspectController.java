package app.infrastructure.web;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dev")
public class H2InspectController {

	private final JdbcTemplate jdbcTemplate;

	public H2InspectController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@GetMapping("/db-inspect")
	public Map<String, List<Map<String, Object>>> inspect() {
		Map<String, List<Map<String, Object>>> result = new HashMap<>();
		result.put("bank_accounts", jdbcTemplate.queryForList("SELECT * FROM bank_accounts"));
		result.put("transfers", jdbcTemplate.queryForList("SELECT * FROM transfers"));
		result.put("operation_logs", jdbcTemplate.queryForList("SELECT * FROM operation_logs"));
		result.put("bank_product_catalog", jdbcTemplate.queryForList("SELECT * FROM bank_product_catalog"));
		return result;
	}
}
