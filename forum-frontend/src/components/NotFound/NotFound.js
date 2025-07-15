import { Link } from 'react-router-dom';

export default function NotFound() {
    return (
        <div className="not-found">
            <h1>Página Não Encontrada</h1>
            <p>Desculpe, a página que você está procurando não existe.</p>
            <Link to="/">Voltar para a página inicial</Link>
        </div>
    );
}