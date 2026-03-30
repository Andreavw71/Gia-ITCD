export function formatCPF(value: string): string {
  const digits = value.replace(/\D/g, '');
  if (digits.length <= 11) {
    return digits.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  }
  return formatCNPJ(digits);
}

export function formatCNPJ(value: string): string {
  const digits = value.replace(/\D/g, '');
  return digits.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5');
}

export function formatCPFOrCNPJ(value: string): string {
  const digits = value.replace(/\D/g, '');
  if (digits.length <= 11) return formatCPF(digits);
  return formatCNPJ(digits);
}

export function formatCurrency(value: number): string {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(value);
}

export function formatNumber(value: number, decimals = 2): string {
  return new Intl.NumberFormat('pt-BR', {
    minimumFractionDigits: decimals,
    maximumFractionDigits: decimals,
  }).format(value);
}

export function formatDate(value: string): string {
  if (!value) return '';
  const date = new Date(value);
  return date.toLocaleDateString('pt-BR');
}

export function formatCEP(value: string): string {
  const digits = value.replace(/\D/g, '');
  return digits.replace(/(\d{5})(\d{3})/, '$1-$2');
}

export function formatPhone(ddd: string, phone: string): string {
  if (!ddd || !phone) return '';
  return `(${ddd}) ${phone}`;
}

export function parseCurrencyToNumber(value: string): number {
  if (!value) return 0;
  const cleaned = value.replace(/[^\d,.-]/g, '').replace('.', '').replace(',', '.');
  return parseFloat(cleaned) || 0;
}

export function onlyDigits(value: string): string {
  return value.replace(/\D/g, '');
}
