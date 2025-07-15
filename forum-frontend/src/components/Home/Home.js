import { Link } from 'react-router-dom';

export default function Home() {
    return (
        <div className="home">
            <h1>Bem-vindo ao Fórum</h1>
            <p>Explore e crie postagens</p>
            <Link to="/posts" className="btn">Ver Postagens</Link>
        </div>
    );
}