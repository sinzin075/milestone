import Link from "next/link";

export default function Home() {
  return (
    <div className="h-screen w-screen flex justify-center items-center bg-green-50">
      <div>
        <div className="bg-green-300 p-6 rounded-3xl text-center mb-6">
          <div className="font-mono font-extrabold text-3xl">~Milestone~</div>
        </div>

        <div className="bg-green-100 p-6 rounded-xl w-80">
          <div className="mb-4">
            <h4 className="mb-1">User Name</h4>
            <input
              type="text"
              placeholder="User Name"
              className="w-full placeholder-gray-400 border-2 rounded-md p-1 mb-2"
            />
            <h4 className="mb-1">Email</h4>
            <input
              type="text"
              placeholder="Password"
              className="w-full placeholder-gray-400 border-2 rounded-md p-1"
            />
          </div>

          <button className="w-full bg-green-300 border-2 border-green-600 text-black rounded-xl py-1 mb-2">
            Login
          </button>

          <div className="text-center">
            <Link
              href="/userRegistration"
              className="inline-block text-sm text-blue-700 hover:underline"
            >
              新規ユーザー登録
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
