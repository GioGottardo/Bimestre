package br.univel.url;

import java.math.BigDecimal;

	public class Produto {
		
		private Long id;
		private String descricao;
		private BigDecimal valorReal;
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public BigDecimal getValorReal() {
			return valorReal;
		}
		public void setValorReal(BigDecimal valorReal) {
			this.valorReal = valorReal;
		}
}
